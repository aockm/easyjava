package com.tockm.buider;

import com.tockm.bean.Constants;
import com.tockm.bean.FieldInfo;
import com.tockm.bean.TableInfo;
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
import java.util.List;


public class BuildTable {
    private static final Logger logger = LoggerFactory.getLogger(BuildTable.class);
    private static Connection connection = null;

    private static String  SQL_SHOW_TABLES_STATUS = "show table status";
    private static String  SQL_SHOW_TABLES_FIELDS = "show full fields from %s";

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
    public static void getTables() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<TableInfo> tables = new ArrayList();
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
                tableInfo.setBeanParamName(beanName);
                List<FieldInfo> fieldInfoList = readFieldInfo(tableInfo);
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
    }

    private static List<FieldInfo> readFieldInfo(TableInfo tableInfo) {
        PreparedStatement preparedStatement = null;
        ResultSet fieldResult = null;
        List<FieldInfo> fieldInfoList = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement(String.format(SQL_SHOW_TABLES_FIELDS, tableInfo.getBeanName()));
            fieldResult = preparedStatement.executeQuery();
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
                fieldInfo.setFieldName(field);
                fieldInfo.setComment(comment);
                fieldInfo.setSqlType(type);
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(processJavaType(type));
                fieldInfo.setAutoIncrement("auto_increment".equalsIgnoreCase(extra)?true:false);
                fieldInfoList.add(fieldInfo);
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPE, type)){
                    tableInfo.setHaveDateTime(true);
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPE, type)){
                    tableInfo.setHaveDate(true);
                }
                if (ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE, type)){
                    tableInfo.setHaveBigDecimal(true);
                }

            }
        }catch (Exception e) {
            logger.error("读取表失败", e);
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
        }else if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPE, type)) {
            return "BigDecimal";
        }else {
            throw new RuntimeException("无法识别的类型"+type);
        }
    }
}
