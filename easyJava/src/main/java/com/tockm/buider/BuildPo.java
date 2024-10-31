package com.tockm.buider;

import com.tockm.bean.Constants;
import com.tockm.bean.FieldInfo;
import com.tockm.bean.TableInfo;
import com.tockm.utils.DateUtils;
import com.tockm.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class BuildPo {
    public static final Logger logger = LoggerFactory.getLogger(BuildPo.class);
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_PO);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File poFile = new File(folder , tableInfo.getBeanName()+".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package "+Constants.PACKAGE_PO+";");
            bw.newLine();
            bw.newLine();
            bw.write("import java.io.Serializable;");
            bw.newLine();
            if (tableInfo.getHaveDate()||tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date;\n");
                bw.write(Constants.BEAN_DATE_FORMAT_CLASS+";\n");
                bw.write(Constants.BEAN_DATE_UNFORMAT_CLASS+";\n");
            }
            //  忽略属性
            Boolean haveIgnoreBean = false;
            for (FieldInfo field: tableInfo.getFieldList()) {
                if (ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FIELD.split(","), field.getPropertyName())) {
                    haveIgnoreBean = true;
                    break;
                }
            }
            if (haveIgnoreBean) {
                bw.write(Constants.IGNORE_BEAN_TOJSON_CLASS+";\n");
                bw.newLine();
            }
            if (tableInfo.getHaveBigDecimal()) {
                bw.write("import java.math.BigDecimal;");
            }
            bw.newLine();
            bw.newLine();
            BuildComment.createClassComment(bw,tableInfo.getComment());
            bw.write("public class "+tableInfo.getBeanName()+" implements Serializable {");
            bw.newLine();
            for (FieldInfo field: tableInfo.getFieldList()){
                if (field.getComment()!=null && !field.getComment().equals("")) BuildComment.createFieldComment(bw, field.getComment());
                if (ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPE,field.getSqlType())) {
                    bw.write("\t"+String.format(Constants.BEAN_DATE_FORMAT_EXPRESSION, DateUtils.YYYY_MM_DD_HH_MM_SS));
                    bw.newLine();
                    bw.write("\t"+String.format(Constants.BEAN_DATE_UNFORMAT_EXPRESSION, DateUtils.YYYY_MM_DD_HH_MM_SS));
                    bw.newLine();
                }
                if (ArrayUtils.contains(Constants.SQL_DATE_TYPE,field.getSqlType())) {
                    bw.write("\t"+String.format(Constants.BEAN_DATE_FORMAT_EXPRESSION, DateUtils.YYYY_MM_DD));
                    bw.newLine();
                    bw.write("\t"+String.format(Constants.BEAN_DATE_UNFORMAT_EXPRESSION, DateUtils.YYYY_MM_DD));
                    bw.newLine();
                }
                if (ArrayUtils.contains(Constants.IGNORE_BEAN_TOJSON_FIELD.split(","),field.getPropertyName())) {
                    bw.write("\t"+String.format(Constants.IGNORE_BEAN_TOJSON_EXPRESSION, DateUtils.YYYY_MM_DD));
                    bw.newLine();
                }
                bw.write("\tprivate " + field.getJavaType()+" " +field.getPropertyName()+";");
                bw.newLine();
                bw.newLine();
            }
            bw.newLine();
            for(FieldInfo field: tableInfo.getFieldList()){
                String tempField = StringUtils.upperCaseFirstLetter(field.getPropertyName());
                bw.write("\tpublic void set"+tempField+"("+field.getJavaType()+" "+field.getPropertyName()+"){\n");
                bw.write("\t\tthis."+field.getPropertyName()+" = "+field.getPropertyName()+";\n");
                bw.write("\t}\n");
                bw.write("\tpublic "+field.getJavaType()+ " get"+tempField+"(){\n");
                bw.write("\t\treturn this."+field.getPropertyName()+";\n");
                bw.write("\t}\n");
            }
            bw.write("}");
            bw.flush();
        }catch (Exception e) {
            logger.error("创建po失败",e);
        }finally {
            if (bw != null) {try {bw.close();} catch (IOException e) {e.printStackTrace();}}
            if (osw != null) {try {osw.close();} catch (IOException e) {e.printStackTrace();}}
            if (out != null) {try {out.close();} catch (IOException e) {e.printStackTrace();}}
        }
    }
}
