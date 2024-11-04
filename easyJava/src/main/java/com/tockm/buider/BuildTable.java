package com.tockm.buider;

import com.tockm.bean.Constants;
import com.tockm.bean.FieldInfo;
import com.tockm.bean.TableInfo;
import com.tockm.utils.JsonUtils;
import com.tockm.utils.PropertiesUtils;
import com.tockm.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BuildTable {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection connection = null;

    private static String  SQL_SHOW_TABLES_STATUS = "show table status";
    private static String  SQL_SHOW_TABLES_FIELDS = "show full fields from %s";
    private static String  SQL_SHOW_TABLES_INDEX = "show index from %s";

    static {
        String driver = PropertiesUtils.getProperty("db.driver.name");
        String url = PropertiesUtils.getProperty("db.url");
        String user = PropertiesUtils.getProperty("db.username");
        String password = PropertiesUtils.getProperty("db.password");
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        }catch (Exception e) {
            logger.error("数据库连接失败", e);
        }
    }
    public static List<TableInfo> getTables() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TableInfo> tableInfoList = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(SQL_SHOW_TABLES_STATUS);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tableName = resultSet.getString("name");
                String comment = resultSet.getString("comment");
                TableInfo tableInfo = new TableInfo();
                tableInfo.setTableName(tableName);
                String beanName = tableName;
                if (Constants.IGNORE_TABLE_PREFIX) {
                    beanName = tableName.substring(beanName.indexOf("_") + 1);
                }
                beanName = processField(beanName,true);
                tableInfo.setBeanName(beanName);
                tableInfo.setComment(comment);
                tableInfo.setBeanParamName(beanName + Constants.SUFFIX_BEAN_QUERY);

                readFieldInfo(tableInfo);

//                getKeyIndexInfo(tableInfo);

                tableInfoList.add(tableInfo);

            }
        }catch (Exception e) {
            logger.error("读取表失败", e);
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return tableInfoList;
    }

    private static List<FieldInfo> readFieldInfo(TableInfo tableInfo) {
        PreparedStatement preparedStatement = null;
        ResultSet fieldResult = null;
        List<FieldInfo> fieldInfoList = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(String.format(SQL_SHOW_TABLES_FIELDS, tableInfo.getTableName()));
            fieldResult = preparedStatement.executeQuery();
            Boolean haveDateTime = false;
            Boolean haveDate = false;
            Boolean haveBigDecimal = false;
            while (fieldResult.next()) {
                String field = fieldResult.getString("field");
                String type = fieldResult.getString("type");
                String extra = fieldResult.getString("extra");
                String comment = fieldResult.getString("comment");
                if (type.indexOf("(") > 0) {
                    type = type.substring(0, type.indexOf("("));
                }
                String propertyName = processField(field,false);
                FieldInfo fieldInfo = new FieldInfo();
                fieldInfoList.add(fieldInfo);

                fieldInfo.setFieldName(field);
                fieldInfo.setComment(comment);
                fieldInfo.setSqlType(type);
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(processJavaType(type));
                fieldInfo.setAutoIncrement("auto_increment".equalsIgnoreCase(extra)?true:false);

                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPE, type)){
                    haveDateTime = true;
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPE, type)){
                    haveDate = true;
                }
                if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE, type)){
                   haveBigDecimal = true;
                }
            }
            tableInfo.setHaveDateTime(haveDateTime);
            tableInfo.setHaveDate(haveDate);
            tableInfo.setHaveBigDecimal(haveBigDecimal);
            tableInfo.setFieldList(fieldInfoList);
            getKeyIndexInfo(tableInfo);

            logger.info("tableInfo:{}", JsonUtils.convertObject2Json(tableInfo));
        }catch (Exception e) {
            logger.error("读取字段失败", e);
        }finally {
            if (fieldResult != null) {
                try {
                    fieldResult.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return fieldInfoList;
    }
    private static List<FieldInfo> getKeyIndexInfo(TableInfo tableInfo) {
        PreparedStatement preparedStatement = null;
        ResultSet fieldResult = null;
        List<FieldInfo> fieldInfoList = new ArrayList();
        try {
            Map<String, FieldInfo> fieldInfoMap = new HashMap();
            for (FieldInfo fieldInfo : tableInfo.getFieldList()) {
                fieldInfoMap.put(fieldInfo.getFieldName(), fieldInfo);
            }
            preparedStatement = connection.prepareStatement(String.format(SQL_SHOW_TABLES_INDEX, tableInfo.getTableName()));
            fieldResult = preparedStatement.executeQuery();
            while (fieldResult.next()) {
                String keyName = fieldResult.getString("key_name");
                Integer nonUnique = fieldResult.getInt("non_unique");
                String columnName = fieldResult.getString("column_name");
                if (nonUnique == 1) {
                    continue;
                }
                List<FieldInfo> keyFieldList = tableInfo.getKeyIndexMap().get(keyName);
                if (null == keyFieldList || keyFieldList.size() == 0) {
                    keyFieldList = new ArrayList();
                    tableInfo.getKeyIndexMap().put(keyName, keyFieldList);
                }
                keyFieldList.add(fieldInfoMap.get(columnName));
                logger.info("添加主键:{}", columnName);
            }
        }catch (Exception e) {
            logger.error("读取索引失败", e);
        }finally {
            if (fieldResult != null) try {
                fieldResult.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return fieldInfoList;
    }

    private static String processField(String field, Boolean upperCaseFieldLetter) {
        StringBuffer sb = new StringBuffer();
        String[] fields = field.split("_");
        sb.append(upperCaseFieldLetter?StringUtils.upperCaseFirstLetter(fields[0]):fields[0]);
        for (int i = 1; i < fields.length; i++) {
            sb.append(StringUtils.upperCaseFirstLetter(fields[i]));
        }
        return sb.toString();
    }

    private static String processJavaType(String type) {
        if (ArrayUtils.contains(Constants.SQL_INTEGER_TYPE, type)) {
            return "Integer";
        }else if (ArrayUtils.contains(Constants.SQL_LONG_TYPE, type)) {
            return "Long";
        }else if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPE, type) || ArrayUtils.contains(Constants.SQL_DATE_TYPE, type)) {
            return "Date";
        }else if(ArrayUtils.contains(Constants.SQL_STRING_TYPE, type)) {
            return "String";
        }else if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE, type)) {
            return "BigDecimal";
        }else {
            throw new RuntimeException("无法识别的类型"+type);
        }
    }
}
