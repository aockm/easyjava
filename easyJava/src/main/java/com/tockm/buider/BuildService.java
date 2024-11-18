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

public class BuildService {
    public static final Logger logger = LoggerFactory.getLogger(BuildService.class);
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_SERVICE);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String className = tableInfo.getBeanName()+"Service";
        File poFile = new File(folder , className+".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package "+Constants.PACKAGE_SERVICE+";\n");
            bw.write("import "+Constants.PACKAGE_PO+"."+tableInfo.getBeanName()+";\n");
            bw.write("import "+Constants.PACKAGE_QUERY+"."+tableInfo.getBeanParamName()+";\n");
            bw.write("import java.util.List;\n");
            bw.write("import "+Constants.PACKAGE_VO+".PaginationResultVo;\n");
            bw.newLine();
            BuildComment.createClassComment(bw,tableInfo.getComment()+"Service");
            bw.write("public interface "+className+" {\n");

            BuildComment.createMethodComment(bw,"根据条件查询列表");
            bw.write("\tList<"+tableInfo.getBeanName()+"> findListByParam("+tableInfo.getBeanParamName()+" param);\n");
            BuildComment.createMethodComment(bw,"根据条件查询数量");
            bw.write("\tInteger findCountByParam("+tableInfo.getBeanParamName()+" param);\n");
            BuildComment.createMethodComment(bw,"分页查询");
            bw.write("\tPaginationResultVo<"+tableInfo.getBeanName()+"> findListByPage("+tableInfo.getBeanParamName()+" param);\n");
            BuildComment.createMethodComment(bw,"新增");
            bw.write("\tInteger add("+tableInfo.getBeanName()+" bean);\n");
            BuildComment.createMethodComment(bw,"批量新增");
            bw.write("\tInteger addBatch(List<"+tableInfo.getBeanName()+"> listBean);\n");
            BuildComment.createMethodComment(bw,"批量新增/修改");
            bw.write("\tInteger addOrUpdateBatch(List<"+tableInfo.getBeanName()+"> bean);\n");
            for (Map.Entry<String, List<FieldInfo>> entry : tableInfo.getKeyIndexMap().entrySet()) {
                List<FieldInfo> keyFieldInfList = entry.getValue();
                Integer index = 0;
                StringBuffer methodName = new StringBuffer();
                StringBuffer methodParam = new StringBuffer();
                for (FieldInfo fieldInfo : keyFieldInfList) {
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    methodParam.append(fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName());
                    if (index < keyFieldInfList.size()) {
                        methodName.append("And");
                        methodParam.append(", ");
                    }
                }
                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"查询");
                bw.write("\t"+tableInfo.getBeanName()+" get"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+");\n");

                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"更新");
                bw.write("\tInteger update"+tableInfo.getBeanName()+"By"+methodName+"("+tableInfo.getBeanName()+" bean, "+methodParam+");\n");

                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"删除");
                bw.write("\tInteger delete"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+");\n");
            }

            bw.write("}");
            bw.flush();
        }catch (Exception e) {
            logger.error("创建service失败",e);
        }finally {
            if (bw != null) {try {bw.close();} catch (IOException e) {e.printStackTrace();}}
            if (osw != null) {try {osw.close();} catch (IOException e) {e.printStackTrace();}}
            if (out != null) {try {out.close();} catch (IOException e) {e.printStackTrace();}}
        }
    }
}
