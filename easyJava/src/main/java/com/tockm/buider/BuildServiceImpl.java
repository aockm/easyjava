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
        String mapperName = tableInfo.getBeanName()+Constants.SUFFIX_MAPPERS;
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
            bw.write("import "+Constants.PACKAGE_QUERY+".SimplePage;\n");
            bw.write("import "+Constants.PACKAGE_MAPPER+"."+mapperName+";\n");
            bw.write("import "+Constants.PACKAGE_ENUMS+".PageSize;\n");
            bw.write("import "+Constants.PACKAGE_VO+".PaginationResultVo;\n");
            bw.write("import java.util.List;\n");
            bw.write("import javax.annotation.Resource;;\n");
            bw.write("import org.springframework.stereotype.Service;\n");
            bw.newLine();
            BuildComment.createClassComment(bw,tableInfo.getComment()+"Service");
            bw.write("@Service(\""+StringUtils.lowerCaseFirstLetter(interfaceName)+"\")\n");
            bw.write("public class "+className+" implements "+interfaceName+" {\n");
            bw.write("\t@Resource\n");

            String queryName = tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY;
            bw.write("\tprivate "+mapperName+"<"+tableInfo.getBeanName()+","+tableInfo.getBeanParamName()+"> "+StringUtils.lowerCaseFirstLetter(mapperName)+";\n");
            BuildComment.createMethodComment(bw,"根据条件查询列表");
            bw.write("\t@Override\n");
            bw.write("\tpublic List<"+tableInfo.getBeanName()+"> findListByParam("+tableInfo.getBeanParamName()+" param){\n");
            bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".selectList(param);\n");
            bw.write("\t}\n\n");
            BuildComment.createMethodComment(bw,"根据条件查询数量");
            bw.write("\t@Override\n");
            bw.write("\tpublic Integer findCountByParam("+tableInfo.getBeanParamName()+" param){\n");
            bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".selectCount(param);\n");
            bw.write("\t}\n\n");
            BuildComment.createMethodComment(bw,"分页查询");
            bw.write("\t@Override\n");
            bw.write("\tpublic PaginationResultVo<"+tableInfo.getBeanName()+"> findListByPage("+tableInfo.getBeanParamName()+" query){\n");
            bw.write("\t\tint count = this.findCountByParam(query);\n");
            bw.write("\t\tint pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();\n");
            bw.write("\t\tSimplePage page = new SimplePage(query.getPageNo(), count, pageSize);\n");
            bw.write("\t\tquery.setSimplePage(page);\n");
            bw.write("\t\tList<"+tableInfo.getBeanName()+"> list = this.findListByParam(query);\n");
            bw.write("\t\tPaginationResultVo<"+tableInfo.getBeanName()+"> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);\n");
            bw.write("\t\treturn result;\n");
            bw.write("\t}\n\n");
            BuildComment.createMethodComment(bw,"新增");
            bw.write("\t@Override\n");
            bw.write("\tpublic Integer add("+tableInfo.getBeanName()+" bean){\n");
            bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".insert(bean);\n");
            bw.write("\t}\n\n");

            BuildComment.createMethodComment(bw,"批量新增");
            bw.write("\t@Override\n");
            bw.write("\tpublic Integer addBatch(List<"+tableInfo.getBeanName()+"> listBean){\n");
            bw.write("\t\tif(listBean==null||listBean.isEmpty()){\n");
            bw.write("\t\t\treturn 0;\n");
            bw.write("\t\t}\n");
            bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".insertBatch(listBean);\n");
            bw.write("\t}\n\n");

            BuildComment.createMethodComment(bw,"批量新增/修改");
            bw.write("\t@Override\n");
            bw.write("\tpublic Integer addOrUpdateBatch(List<"+tableInfo.getBeanName()+"> listBean){\n");
            bw.write("\t\tif(listBean==null||listBean.isEmpty()){\n");
            bw.write("\t\t\treturn 0;\n");
            bw.write("\t\t}\n");
            bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".insertOrUpdateBatch(listBean);\n");
            bw.write("\t}\n\n");
            for (Map.Entry<String, List<FieldInfo>> entry : tableInfo.getKeyIndexMap().entrySet()) {
                List<FieldInfo> keyFieldInfList = entry.getValue();
                Integer index = 0;
                StringBuilder methodName = new StringBuilder();
                StringBuilder methodParam = new StringBuilder();
                StringBuilder paramBuilder = new StringBuilder();
                for (FieldInfo fieldInfo : keyFieldInfList) {
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    methodParam.append(fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName());
                    paramBuilder.append(fieldInfo.getPropertyName());
                    if (index < keyFieldInfList.size()) {
                        methodName.append("And");
                        methodParam.append(", ");
                        paramBuilder.append(", ");
                    }
                }
                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"查询");
                bw.write("\t@Override\n");
                bw.write("\tpublic "+tableInfo.getBeanName()+" get"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+"){\n");
                bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".selectBy"+methodName+"("+paramBuilder+");\n");
                bw.write("\t}\n\n");


                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"更新");
                bw.write("\tpublic Integer update"+tableInfo.getBeanName()+"By"+methodName+"("+tableInfo.getBeanName()+" bean, "+methodParam+"){\n");
                bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".updateBy"+methodName+"(bean,"+paramBuilder+");\n");

                bw.write("\t}\n\n");
                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"删除");
                bw.write("\tpublic Integer delete"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+"){\n");
                bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(mapperName)+".deleteBy"+methodName+"("+paramBuilder+");\n");

                bw.write("\t}\n\n");
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
