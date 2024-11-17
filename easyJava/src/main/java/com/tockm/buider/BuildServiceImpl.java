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

public class BuildServiceImpl {
    public static final Logger logger = LoggerFactory.getLogger(BuildServiceImpl.class);
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_SERVICE_IMPL);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String className = tableInfo.getBeanName()+"ServiceImpl";
        String interfaceName = tableInfo.getBeanName()+"Service";
        File poFile = new File(folder , className+".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package "+Constants.PACKAGE_SERVICE_IMPL+";\n");
            bw.write("import "+Constants.PACKAGE_SERVICE+"."+interfaceName+";\n");
            bw.write("import "+Constants.PACKAGE_PO+"."+tableInfo.getBeanName()+";\n");
            bw.write("import "+Constants.PACKAGE_QUERY+"."+tableInfo.getBeanParamName()+";\n");
            bw.write("import "+Constants.PACKAGE_VO+".PaginationResultVo;\n");
            bw.write("import java.util.List;\n");
            bw.write("import org.springframework.stereotype.Service;;\n");
            bw.newLine();
            BuildComment.createClassComment(bw,tableInfo.getComment()+"Service");
            bw.write("@Service(\""+StringUtils.lowerCaseFirstLetter(interfaceName)+"\")\n");
            bw.write("public class "+className+" implements "+interfaceName+" {\n");

            BuildComment.createMethodComment(bw,"根据条件查询列表");
            bw.write("\tpublic List<"+tableInfo.getBeanName()+"> findListByParam("+tableInfo.getBeanParamName()+" param){\n");
            bw.write("\t}\n");
            BuildComment.createMethodComment(bw,"根据条件查询数量");
            bw.write("\tpublic Integer findCountByParam("+tableInfo.getBeanParamName()+" param){\n");
            bw.write("\t}\n");

            BuildComment.createMethodComment(bw,"分页查询");
            bw.write("\tpublic PaginationResultVo<"+tableInfo.getBeanName()+"> findListByPage("+tableInfo.getBeanParamName()+" param){\n");
            bw.write("\t}\n");
            BuildComment.createMethodComment(bw,"新增");
            bw.write("\tpublic Integer add("+tableInfo.getBeanName()+" bean){\n");
            bw.write("\t}\n");
            BuildComment.createMethodComment(bw,"批量新增");
            bw.write("\tpublic Integer addBatch(List<"+tableInfo.getBeanName()+"> listBean){\n");
            bw.write("\t}\n");

            BuildComment.createMethodComment(bw,"批量新增/修改");
            bw.write("\tpublic Integer addOrUpdateBatch("+tableInfo.getBeanName()+" bean){\n");
            bw.write("\t}\n");
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
                bw.write("\tpublic "+tableInfo.getBeanName()+" getBy"+methodName+"("+methodParam+"){\n");
                bw.write("\t}\n");


                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"更新");
                bw.write("\tpublic Integer updateBy"+methodName+"("+tableInfo.getBeanName()+" bean, "+methodParam+"){\n");
                bw.write("\t}\n");
                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"删除");
                bw.write("\tpublic Integer deleteBy"+methodName+"("+methodParam+"){\n");
                bw.write("\t}\n");
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