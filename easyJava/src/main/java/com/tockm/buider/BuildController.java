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

public class BuildController {
    public static final Logger logger = LoggerFactory.getLogger(BuildController.class);
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_CONTROLLER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String className = tableInfo.getBeanName()+"Controller";
        String interfaceName = tableInfo.getBeanName()+"Service";
        String serviceName = tableInfo.getBeanName()+"Service";
        File poFile = new File(folder , className+".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package "+Constants.PACKAGE_CONTROLLER+";\n");
            bw.write("import "+Constants.PACKAGE_SERVICE+"."+interfaceName+";\n");
            bw.write("import "+Constants.PACKAGE_PO+"."+tableInfo.getBeanName()+";\n");
            bw.write("import "+Constants.PACKAGE_QUERY+"."+tableInfo.getBeanParamName()+";\n");
            bw.write("import "+Constants.PACKAGE_VO+".ResponseVo;\n");
            bw.write("import java.util.List;\n");
            bw.write("import javax.annotation.Resource;\n");
            bw.write("import org.springframework.web.bind.annotation.RequestBody;\n");
            bw.write("import org.springframework.web.bind.annotation.RestController;\n");
            bw.write("import org.springframework.web.bind.annotation.RequestMapping;\n");

            bw.newLine();
            BuildComment.createClassComment(bw,tableInfo.getComment()+"Service");
            bw.write("@RestController\n");
            bw.write("public class "+className+" extends ABaseController {\n");
            bw.write("\t@Resource\n");

            String queryName = tableInfo.getBeanName()+Constants.SUFFIX_BEAN_QUERY;
            bw.write("\tprivate "+serviceName+" "+StringUtils.lowerCaseFirstLetter(serviceName)+";\n");

//            BuildComment.createMethodComment(bw,"根据条件查询列表");bw.write("\tpublic List<"+tableInfo.getBeanName()+"> findListByParam("+tableInfo.getBeanParamName()+" param){\n");
//            bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(serviceName)+".findListByParam(param);\n");
//            bw.write("\t}\n\n");
//            BuildComment.createMethodComment(bw,"根据条件查询数量");
//
//            bw.write("\tpublic Integer findCountByParam("+tableInfo.getBeanParamName()+" param){\n");
//            bw.write("\t\treturn this."+StringUtils.lowerCaseFirstLetter(serviceName)+".findCountByParam(param);\n");
//            bw.write("\t}\n\n");
//            BuildComment.createMethodComment(bw,"分页查询");
//            bw.write("\tpublic PaginationResultVo<"+tableInfo.getBeanName()+"> findListByPage("+tableInfo.getBeanParamName()+" query){\n");
//            bw.write("\t\tint count = this.findCountByParam(query);\n");
//            bw.write("\t\tint pageSize = query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();\n");
//            bw.write("\t\tSimplePage page = new SimplePage(query.getPageNo(), count, pageSize);\n");
//            bw.write("\t\tquery.setSimplePage(page);\n");
//            bw.write("\t\tList<"+tableInfo.getBeanName()+"> list = this.findListByParam(query);\n");
//            bw.write("\t\tPaginationResultVo<"+tableInfo.getBeanName()+"> result = new PaginationResultVo(count,page.getPageSize(),page.getPageNo(),page.getTotalPage(),list);\n");
//            bw.write("\t\treturn result;\n");
//            bw.write("\t}\n\n");
            BuildComment.createMethodComment(bw,"根据条件分页查询");
            bw.write("\t@RequestMapping(\"loadDataList\")\n");
            bw.write("\tpublic ResponseVo loadDataList("+queryName+" query){\n");
            bw.write("\t\treturn getSuccessResponseVo("+StringUtils.lowerCaseFirstLetter(serviceName)+".findListByPage(query));\n");
            bw.write("\t}\n\n");

            BuildComment.createMethodComment(bw,"新增");
            bw.write("\tpublic ResponseVo add("+tableInfo.getBeanName()+" bean){\n");
            bw.write("\t\t"+StringUtils.lowerCaseFirstLetter(serviceName)+".add(bean);\n");
            bw.write("\t\treturn getSuccessResponseVo(null);\n");
            bw.write("\t}\n\n");

            BuildComment.createMethodComment(bw,"批量新增");
            bw.write("\tpublic ResponseVo addBatch(@RequestBody List<"+tableInfo.getBeanName()+"> listBean){\n");
            bw.write("\t\t"+StringUtils.lowerCaseFirstLetter(serviceName)+".addBatch(listBean);\n");
            bw.write("\t\treturn getSuccessResponseVo(null);\n");
            bw.write("\t}\n\n");

            BuildComment.createMethodComment(bw,"批量新增/修改");
            bw.write("\tpublic ResponseVo addOrUpdateBatch(@RequestBody List<"+tableInfo.getBeanName()+"> listBean){\n");
            bw.write("\t\t"+StringUtils.lowerCaseFirstLetter(serviceName)+".addOrUpdateBatch(listBean);\n");
            bw.write("\t\treturn getSuccessResponseVo(null);\n");
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
                bw.write("\tpublic ResponseVo get"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+"){\n");
                bw.write("\t\treturn getSuccessResponseVo("+StringUtils.lowerCaseFirstLetter(serviceName)+".get"+tableInfo.getBeanName()+"By"+methodName+"("+paramBuilder+"));\n");
                bw.write("\t}\n\n");


                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"更新");
                bw.write("\tpublic ResponseVo update"+tableInfo.getBeanName()+"By"+methodName+"("+tableInfo.getBeanName()+" bean, "+methodParam+"){\n");
                bw.write("\t\treturn getSuccessResponseVo("+StringUtils.lowerCaseFirstLetter(serviceName)+".update"+tableInfo.getBeanName()+"By"+methodName+"(bean,"+paramBuilder+"));\n");

                bw.write("\t}\n\n");
                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"删除");
                bw.write("\tpublic ResponseVo delete"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+"){\n");
                bw.write("\t\treturn getSuccessResponseVo("+StringUtils.lowerCaseFirstLetter(serviceName)+".delete"+tableInfo.getBeanName()+"By"+methodName+"("+paramBuilder+"));\n");

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
