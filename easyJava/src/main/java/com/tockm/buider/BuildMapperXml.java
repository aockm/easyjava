package com.tockm.buider;

import com.tockm.bean.Constants;
import com.tockm.bean.FieldInfo;
import com.tockm.bean.TableInfo;
import com.tockm.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapperXml {
    public static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

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
            bw.write("\t<resultMap id=\"base_result_map\" type=\""+poName+"\">\n");

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
