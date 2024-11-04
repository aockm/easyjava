package com.tockm.buider;

import com.tockm.bean.Constants;
import com.tockm.bean.FieldInfo;
import com.tockm.bean.TableInfo;
import com.tockm.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildQuery {
    public static final Logger logger = LoggerFactory.getLogger(BuildQuery.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_QUERY);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String className = tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY;
        File poFile = new File(folder , className+".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package "+Constants.PACKAGE_QUERY+";");
            bw.newLine();
            bw.newLine();
            if (tableInfo.getHaveDate()||tableInfo.getHaveDateTime()) {
                bw.write("import java.util.Date;\n");
            }

            if (tableInfo.getHaveBigDecimal()) {
                bw.write("import java.math.BigDecimal;");
            }
            bw.newLine();
            bw.newLine();
            BuildComment.createClassComment(bw,tableInfo.getComment()+"查询");
            bw.write("public class "+className+" {");
            bw.newLine();
            List<FieldInfo> extendList = new ArrayList();

            for (FieldInfo field: tableInfo.getFieldList()){
                if (field.getComment()!=null && !field.getComment().equals("")){
                    BuildComment.createFieldComment(bw, field.getComment());
                }

                bw.write("\tprivate " + field.getJavaType()+" " +field.getPropertyName()+";");
                bw.newLine();
                bw.newLine();
                // String类型参数
                if(ArrayUtils.contains(Constants.SQL_STRING_TYPE,field.getSqlType())){
                    String properName = field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_FUZZY;
                    bw.write("\tprivate " + field.getJavaType()+" " +properName+";\n");
                    bw.newLine();
                    FieldInfo extendField = new FieldInfo();
                    extendField.setJavaType(field.getJavaType());
                    extendField.setPropertyName(properName);
                    extendList.add(extendField);
                }
                if(ArrayUtils.contains(Constants.SQL_DATE_TYPE,field.getSqlType())||ArrayUtils.contains(Constants.SQL_DATE_TYPE,field.getSqlType())){
                    String properStartName = field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_START;
                    String properEndName = field.getPropertyName() + Constants.SUFFIX_BEAN_QUERY_TIME_END;
                    bw.write("\tprivate String " + properStartName+";\n");
                    bw.newLine();
                    bw.write("\tprivate String " + properEndName+";\n");
                    bw.newLine();

                    FieldInfo timeStartField = new FieldInfo();
                    timeStartField.setJavaType("String");
                    timeStartField.setPropertyName(properStartName);
                    extendList.add(timeStartField);

                    FieldInfo timeEndField = new FieldInfo();
                    timeEndField.setJavaType("String");
                    timeEndField.setPropertyName(properEndName);
                    extendList.add(timeEndField);
                }
            }
            bw.newLine();
            List<FieldInfo> extendFieldList = tableInfo.getFieldList();
            buildGetSet(bw, extendFieldList);
            buildGetSet(bw, extendList);


            bw.write("}");
            bw.flush();
        }catch (Exception e) {
            logger.error("创建query失败",e);
        }finally {
            if (bw != null) {try {bw.close();} catch (IOException e) {e.printStackTrace();}}
            if (osw != null) {try {osw.close();} catch (IOException e) {e.printStackTrace();}}
            if (out != null) {try {out.close();} catch (IOException e) {e.printStackTrace();}}
        }
    }

    private static void buildGetSet(BufferedWriter bw, List<FieldInfo> extendFieldList) throws IOException {
        for(FieldInfo field: extendFieldList){
            String tempField = StringUtils.upperCaseFirstLetter(field.getPropertyName());
            bw.write("\tpublic void set"+tempField+"("+field.getJavaType()+" "+field.getPropertyName()+"){\n");
            bw.write("\t\tthis."+field.getPropertyName()+" = "+field.getPropertyName()+";\n");
            bw.write("\t}\n");
            bw.newLine();
            bw.write("\tpublic "+field.getJavaType()+ " get"+tempField+"(){\n");
            bw.write("\t\treturn this."+field.getPropertyName()+";\n");
            bw.write("\t}\n");
            bw.newLine();
        }
    }
}
