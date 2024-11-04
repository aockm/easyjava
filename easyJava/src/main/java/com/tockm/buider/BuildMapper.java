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

public class BuildMapper {
    public static final Logger logger = LoggerFactory.getLogger(BuildMapper.class);

    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.PATH_MAPPER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String className = tableInfo.getBeanName()+Constants.SUFFIX_MAPPERS;
        File poFile = new File(folder , className+".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(poFile);
            osw = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(osw);
            bw.write("package "+Constants.PACKAGE_MAPPER+";");
            bw.newLine();
            bw.newLine();

            bw.write("import org.apache.ibatis.annotations.Param;");
            bw.newLine();
            bw.newLine();
            // 注解
            BuildComment.createClassComment(bw,tableInfo.getComment()+"Mapper");
            bw.write("public interface "+className+"<T,P> extends BaseMapper {\n");
            Map<String, List<FieldInfo>> keyIndexMap = tableInfo.getKeyIndexMap();
            for (Map.Entry<String, List<FieldInfo>> entry : keyIndexMap.entrySet()) {
                List<FieldInfo> keyFieldInfList = entry.getValue();
                Integer index = 0;
                StringBuffer methodName = new StringBuffer();
                StringBuffer methodParam = new StringBuffer();
                for (FieldInfo fieldInfo : keyFieldInfList) {
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    methodParam.append("@Param(\""+fieldInfo.getPropertyName()+"\") "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName());
                    if (index < keyFieldInfList.size()) {
                        methodName.append("And");
                        methodParam.append(", ");
                    }
                }
                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"查询");
                bw.write("\tT selectBy"+methodName+"("+methodParam+");\n");

                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"更新");
                bw.write("\tInteger updateBy"+methodName+"(@Param(\"bean\") T t, "+methodParam+");\n");

                bw.newLine();
                BuildComment.createFieldComment(bw,"根据"+methodName+"删除");
                bw.write("\tInteger deleteBy"+methodName+"("+methodParam+");\n");
            }
            bw.newLine();
            bw.write("}");
            bw.flush();
        }catch (Exception e) {
            logger.error("创建mapper失败",e);
        }finally {
            if (bw != null) {try {bw.close();} catch (IOException e) {e.printStackTrace();}}
            if (osw != null) {try {osw.close();} catch (IOException e) {e.printStackTrace();}}
            if (out != null) {try {out.close();} catch (IOException e) {e.printStackTrace();}}
        }
    }
}
