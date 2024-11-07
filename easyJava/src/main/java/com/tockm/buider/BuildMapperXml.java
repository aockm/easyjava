package com.tockm.buider;

import com.tockm.bean.Constants;
import com.tockm.bean.FieldInfo;
import com.tockm.bean.TableInfo;
import com.tockm.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapperXml {
    public static final Logger logger = LoggerFactory.getLogger(BuildMapperXml.class);

    private static final String QUERY_CONDITION = "query_condition";
    private static final String BASE_COLUMN_LIST = "base_column_list";
    private static final String BASE_RESULT_MAP = "base_result_map";
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_MAPPER_XML);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        logger.info(Constants.PATH_MAPPER_XML);
        String className = tableInfo.getBeanName()+Constants.SUFFIX_MAPPERS;
        File poFile = new File(folder , className+".xml");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);

            String packageName = Constants.PACKAGE_MAPPER+"."+className;
            String poName = Constants.PACKAGE_PO+"."+tableInfo.getBeanName();
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
            bw.write("<mapper namespace=\""+packageName+"\">\n");
            bw.write("\t<!--实体类映射-->\n");
            bw.write("\t<resultMap id=\""+BASE_RESULT_MAP+"\" type=\""+poName+"\">\n");

            FieldInfo idField = new FieldInfo();
            Map<String, List<FieldInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
            for (Map.Entry<String, List<FieldInfo>> entry : keyIndexMap.entrySet()) {
                if (entry.getKey().equals("PRIMARY")) {
                    List<FieldInfo> fieldInfos = entry.getValue();
                    if (fieldInfos.size() == 1) {
                        idField = fieldInfos.get(0);
                        break;
                    }
                }
            }

            for (FieldInfo field:tableInfo.getFieldList()) {
                String key = "";

                bw.write("\t\t<!--"+field.getComment()+"-->\n");
                if (idField != null && field.getPropertyName().equals(idField.getPropertyName())) {
                    key = "id";
                }else {
                    key = "result";
                }
                bw.write("\t\t<"+key+" column=\""+field.getPropertyName()+"\" property=\""+field.getPropertyName()+"\"/>\n");
            }
            bw.write("\t</resultMap>\n");
            bw.newLine();
            bw.write("\t<!--通用查询结果列-->\n");
            bw.write("\t<sql id=\""+BASE_COLUMN_LIST+"\" >\n");
            StringBuilder columns = new StringBuilder();
            for (FieldInfo field:tableInfo.getFieldList()) {
                columns.append(field.getFieldName()).append(",");
            }
            String columnBuild = columns.substring(0,columns.lastIndexOf(","));
            bw.write("\t\t"+columnBuild+"\n");
            bw.write("\t</sql>\n\n");

            bw.write("\t<!--基础查询条件-->\n");
            bw.write("\t<sql id=\"base_query_condition\" >\n");
            for (FieldInfo field:tableInfo.getFieldList()) {
                String strQuery = "";
                if (ArrayUtils.contains(Constants.SQL_STRING_TYPE,field.getSqlType())) {
                    strQuery = " and query." + field.getPropertyName() + "!='' ";
                }
                bw.write("\t\t<if test=\"query."+field.getPropertyName()+"!=null"+strQuery+"\">\n");
                bw.write("\t\t\tand "+field.getFieldName()+" = #{query."+field.getPropertyName()+"}\n");
                bw.write("\t\t</if>\n");
            }
            bw.write("\t</sql>\n\n");

            bw.write("\t<!--扩展查询条件列-->\n");
            bw.write("\t<sql id=\"query_condition_extend\" >\n");
            for (FieldInfo field:tableInfo.getFieldExtendList()) {
                String andWhere = "";
                if (ArrayUtils.contains(Constants.SQL_STRING_TYPE,field.getSqlType())) {
                    andWhere = " and " + field.getFieldName() + " like concat ('%',#{query."+field.getPropertyName()+"}, '%')";
                }else if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPE,field.getSqlType())||ArrayUtils.contains(Constants.SQL_DATE_TYPE,field.getSqlType())) {
                    if (field.getPropertyName().endsWith(Constants.SUFFIX_BEAN_QUERY_TIME_START)) {
                        andWhere = "<![CDATA[ and "+field.getFieldName()+" >= str_to_date(#{query."+field.getPropertyName()+"}, '%Y-%m-%d')]]>";

                    }else if (field.getPropertyName().endsWith(Constants.SUFFIX_BEAN_QUERY_TIME_END)) {
                        andWhere = "<![CDATA[ and "+field.getFieldName()+" < date_sub(str_to_date(#{query."+field.getPropertyName()+"},' %Y-%m-%d'), interval - 1 day)]]>";
                    }
                }
                bw.write("\t\t<if test=\"query."+field.getPropertyName()+"!=null and query." + field.getPropertyName() + "!='' \">\n");
                bw.write("\t\t\t"+andWhere+"\n");
                bw.write("\t\t</if>\n");
            }
            bw.write("\t</sql>\n\n");

            bw.write("\t<!--扩展的查询条件-->\n");
            bw.write("\t<sql id=\""+QUERY_CONDITION+"\" >\n");
            bw.write("\t\t<where>\n");
            bw.write("\t\t\t<include refid=\"base_query_condition\" />\n");
            bw.write("\t\t\t<include refid=\"query_condition_extend\" />\n");
            bw.write("\t\t</where>\n\n");
            bw.write("\t</sql>\n\n");
            //  查询集合
            bw.write("\t<!--查询集合-->\n");
            bw.write("\t<select id=\"selectList\" resultMap=\""+BASE_RESULT_MAP+"\">\n");
            bw.write("\t\tSELECT <include refid=\""+BASE_COLUMN_LIST+"\"/> FROM "+tableInfo.getTableName()+"\n");
            bw.write("\t\t<include refid=\""+QUERY_CONDITION+"\"/>\n");
            bw.write("\t\t<if test=\"query.orderBy!=null\">order by ${query.orderBy}</if>\n");
            bw.write("\t\t<if test=\"query.simplePage!=null\">limit #{query.simplePage.start}, #{query.simplePage.end}</if>\n");
            bw.write("\t</select>\n\n");

            //  查询数量
            bw.write("\t<!--查询集合-->\n");
            bw.write("\t<select id=\"selectCount\" resultType=\"java.lang.Integer\">\n");
            bw.write("\t\tSELECT count(1) FROM "+tableInfo.getTableName()+"\n");
            bw.write("\t\t<include refid=\""+QUERY_CONDITION+"\"/>\n");
            bw.write("\t</select>\n\n");

            // 插入（匹配有值字段）
            FieldInfo autoIncrementField = null;
            for(FieldInfo field:tableInfo.getFieldList()) {
                if (field.getAutoIncrement()!=null&&field.getAutoIncrement()) {
                    autoIncrementField = field;
                    break;
                }
            }
            bw.write("\t<!--插入（匹配有值字段）-->\n");
            bw.write("\t<insert id=\"insert\" parameterType=\""+poName+"\">\n");
            if (autoIncrementField!=null) {
                bw.write("\t\t<selectKey keyProperty=\"bean."+autoIncrementField.getFieldName()+"\" resultType=\""+autoIncrementField.getJavaType()+"\" order=\"AFTER\">\n");
                bw.write("\t\t\tSELECT LAST_INSERT_ID()\n");
                bw.write("\t\t</selectKey>\n");
            }

            bw.write("\t\tINSERT INTO "+tableInfo.getTableName()+"\n");
            bw.write("\t\t<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n");
            for (FieldInfo field:tableInfo.getFieldList()) {
                bw.write("\t\t\t<if test=\"bean."+field.getPropertyName()+"!=null\">\n");
                bw.write("\t\t\t\t"+field.getFieldName()+",\n");
                bw.write("\t\t\t</if>\n");
            }
            bw.write("\t\t</trim>\n");

            bw.write("\t\t<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n");

            for (FieldInfo field:tableInfo.getFieldList()) {
                bw.write("\t\t\t<if test=\"bean."+field.getPropertyName()+"!=null\">\n");
                bw.write("\t\t\t\t#{bean."+field.getPropertyName()+"},\n");
                bw.write("\t\t\t</if>\n");
            }
            bw.write("\t\t</trim>\n");
            bw.write("\t</insert>\n\n");

            //插入或更新
            bw.write("\t<!--插入或更新（匹配有值字段）-->\n");
            bw.write("\t<insert id=\"insertOrUpdate\" parameterType=\""+poName+"\">\n");
            bw.write("\t\tINSERT INTO "+tableInfo.getTableName()+"\n");
            bw.write("\t\t<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">\n");
            for (FieldInfo field:tableInfo.getFieldList()) {
                bw.write("\t\t\t<if test=\"bean."+field.getPropertyName()+"!=null\">\n");
                bw.write("\t\t\t\t"+field.getFieldName()+",\n");
                bw.write("\t\t\t</if>\n");
            }
            bw.write("\t\t</trim>\n");
            bw.write("\t\t<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">\n");

            for (FieldInfo field:tableInfo.getFieldList()) {
                bw.write("\t\t\t<if test=\"bean."+field.getPropertyName()+"!=null\">\n");
                bw.write("\t\t\t\t#{bean."+field.getPropertyName()+"},\n");
                bw.write("\t\t\t</if>\n");
            }
            bw.write("\t\t</trim>\n");
            bw.write("\t\ton DUPLICATE key update\n");

            bw.write("\t\t<trim prefix=\"\" suffix=\"\" suffixOverrides=\",\">\n");
            for (FieldInfo field:tableInfo.getFieldList()) {
                bw.write("\t\t\t<if test=\"bean."+field.getPropertyName()+"!=null\">\n");
                bw.write("\t\t\t\t"+field.getFieldName()+" = VALUES("+field.getFieldName()+"),\n");
                bw.write("\t\t\t</if>\n");
            }
            bw.write("\t\t</trim>\n");

            bw.write("\t</insert>\n\n");
            //  添加（批量插入）
            bw.write("\t<!--添加（批量插入）-->\n");
            bw.write("\t<insert id=\"insertBatch\" parameterType=\""+poName+"\">\n");
            StringBuffer fields = new StringBuffer();
            for (FieldInfo field:tableInfo.getFieldList()) {
                if (field.getAutoIncrement()!=null&&field.getAutoIncrement()) {continue;}
                fields.append(field.getFieldName()).append(",");
            }
            String fieldsStr = fields.substring(0, fields.lastIndexOf(","));
            bw.write("\t\tINSERT INTO "+tableInfo.getTableName()+"("+fieldsStr+")values\n");
            bw.write("\t\t<foreach collection=\"list\" item=\"item\" separator=\",\" open=\"(\" close=\")\">\n");
            StringBuffer propertyNames = new StringBuffer();
            for (FieldInfo field:tableInfo.getFieldList()) {
                if (field.getAutoIncrement()!=null&&field.getAutoIncrement()) {continue;}
                propertyNames.append("#{item."+field.getPropertyName()).append("},");
            }
            String propertyNamesStr = propertyNames.substring(0, propertyNames.lastIndexOf(","));
            bw.write("\t\t\t"+propertyNamesStr+"\n");
            bw.write("\t\t</foreach>\n");
            bw.write("\t</insert>\n\n");


            //  批量添加 修改（批量插入）
            bw.write("\t<!-- 批量添加 修改（批量插入）-->\n");
            bw.write("\t<insert id=\"insertOrUpdateBatch\" parameterType=\""+poName+"\">\n");
            bw.write("\t\tINSERT INTO "+tableInfo.getTableName()+"("+fieldsStr+")values\n");
            bw.write("\t\t<foreach collection=\"list\" item=\"item\" separator=\",\" open=\"(\" close=\")\">\n");
            bw.write("\t\t\t"+propertyNamesStr+"\n");
            bw.write("\t\t</foreach>\n");
            bw.write("\t\ton DUPLICATE key update\n");
            Integer indexSize = 0;
            for (FieldInfo field:tableInfo.getFieldList()) {
                indexSize ++;
                if (field.getAutoIncrement()!=null&&field.getAutoIncrement()) {continue;}
                if (indexSize < tableInfo.getFieldList().size()-1) {
                    bw.write("\t\t"+field.getFieldName()+" = VALUES("+field.getPropertyName()+"),\n");
                }else if (indexSize == tableInfo.getFieldList().size()-1){
                    bw.write("\t\t"+field.getFieldName()+" = VALUES("+field.getPropertyName()+")\n");
                }
            }
            bw.write("\t</insert>\n\n");

            // 根据主键修改
            bw.write("\t<!-- 根据主键修改 -->\n");

            for (Map.Entry<String, List<FieldInfo>> entry : keyIndexMap.entrySet()) {
                List<FieldInfo> keyFieldInfList = entry.getValue();
                Integer index = 0;
                StringBuffer methodName = new StringBuffer();
                StringBuffer methodParam = new StringBuffer();

                for (FieldInfo fieldInfo : keyFieldInfList) {
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    methodParam.append(fieldInfo.getFieldName()+"=#{"+fieldInfo.getPropertyName()+"}");
                    if (index < keyFieldInfList.size()) {
                        methodName.append("And");
                        methodParam.append(" and ");
                    }
                }

                bw.write("\t<!-- 根据"+methodName+"查询 -->\n");
                bw.write("\t<select id=\"selectBy"+methodName+"\" resultMap=\"base_result_map\">\n");
                bw.write("\t\tselect <include refid=\"base_column_list\"/>\n");
                bw.write("\t\tfrom "+tableInfo.getTableName()+"\n");
                bw.write("\t\twhere "+methodParam+"\n");
                bw.write("\t</select>\n\n");

                bw.write("\t<!-- 根据"+methodName+"更新 -->\n");
                bw.write("\t<update id=\"updateBy"+methodName+"\" parameterType=\""+poName+"\">\n");
                bw.write("\t\tupdate "+tableInfo.getTableName()+"\n");
                bw.write("\t\t<set>\n");
                for (FieldInfo field:tableInfo.getFieldList()) {
                    if (field.getAutoIncrement()!=null&&field.getAutoIncrement()) {continue;}
                    bw.write("\t\t\t<if test=\""+field.getPropertyName()+"!=null\">\n");
                    bw.write("\t\t\t\t"+field.getFieldName()+" = #{bean."+field.getPropertyName()+"},\n");
                    bw.write("\t\t\t</if>\n");
                }
                bw.write("\t\t</set>\n");
                bw.write("\t</update>\n\n");

                bw.write("\t<!-- 根据"+methodName+"删除 -->\n");
                bw.write("\t<delete id=\"deleteBy"+methodName+"\">\n");
                bw.write("\t\tdelete from "+tableInfo.getTableName()+"\n");
                bw.write("\t\twhere "+methodParam+"\n");
                bw.write("\t</delete>\n\n");
            }
            bw.write("</mapper>\n");
            bw.newLine();

            bw.flush();
        }catch (Exception e) {
            logger.error("创建xml失败",e);
        }finally {
            if (bw != null) {try {bw.close();} catch (IOException e) {e.printStackTrace();}}
            if (osw != null) {try {osw.close();} catch (IOException e) {e.printStackTrace();}}
            if (out != null) {try {out.close();} catch (IOException e) {e.printStackTrace();}}
        }
    }
}
