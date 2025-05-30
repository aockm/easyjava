package com.tockm.buider;

import com.tockm.bean.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildBase {

    private static final Logger logger = LoggerFactory.getLogger(BuildBase.class);
    public static void execute(){
        List<String> headerInfoList = new ArrayList();
        // 生成date枚举

        headerInfoList.add("package "+Constants.PACKAGE_ENUMS+";");
        build(headerInfoList,"DateTimePatternEnum", Constants.PATH_ENUMS);
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_UTILS+";");
        build(headerInfoList,"DateUtils", Constants.PATH_UTILS);

        // 生成baseMapper
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_MAPPER+";");
        build(headerInfoList,"BaseMapper", Constants.PATH_MAPPER);

        // 生成pageSize枚举
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_ENUMS+";");

        build(headerInfoList,"PageSize", Constants.PATH_ENUMS);
        // 生成ResponseCode枚举
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_ENUMS+";");

        build(headerInfoList,"ResponseCodeEnum", Constants.PATH_ENUMS);
        // 生成分页
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_QUERY+";");
        headerInfoList.add("import "+Constants.PACKAGE_ENUMS+".PageSize;");
        build(headerInfoList,"SimplePage", Constants.PATH_QUERY);
        // 生成查询参数
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_QUERY+";");
        build(headerInfoList,"BaseQuery", Constants.PATH_QUERY);

        // 生成PaginationResultVo
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_VO+";");
        build(headerInfoList,"PaginationResultVo", Constants.PATH_VO);
        // 生成ResponseVo
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_VO+";");
        build(headerInfoList,"ResponseVo", Constants.PATH_VO);

        // 生成ABaseController
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_CONTROLLER+";");
        headerInfoList.add("import "+Constants.PACKAGE_EXCEPTION+".BusinessException;");
        headerInfoList.add("import "+Constants.PACKAGE_VO+".ResponseVo;");
        headerInfoList.add("import "+Constants.PACKAGE_ENUMS+".ResponseCodeEnum;\n");

        build(headerInfoList,"ABaseController", Constants.PATH_CONTROLLER);
        // 生成AGlobalExceptionHandleController
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_CONTROLLER+";");
        headerInfoList.add("import "+Constants.PACKAGE_VO+".ResponseVo;");
        headerInfoList.add("import "+Constants.PACKAGE_ENUMS+".ResponseCodeEnum;");
        headerInfoList.add("import "+Constants.PACKAGE_EXCEPTION+".BusinessException;");

        build(headerInfoList,"AGlobalExceptionHandlerController", Constants.PATH_CONTROLLER);

        // 生成BusinessException
        headerInfoList.clear();
        headerInfoList.add("package "+Constants.PACKAGE_EXCEPTION+";");
        headerInfoList.add("import "+Constants.PACKAGE_ENUMS+".ResponseCodeEnum;\n");
        build(headerInfoList,"BusinessException", Constants.PATH_EXCEPTION);

    }

    public static void build(List<String> headerInfo, String fileName, String outPutPath) {
        File folder = new File(outPutPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File javaFile = new File(outPutPath , fileName+".java");
        OutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        InputStream in = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try{
            out = new FileOutputStream(javaFile);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            String templatePath = BuildBase.class.getClassLoader().getResource("template/"+fileName+".txt").getPath();
            in = new FileInputStream(templatePath);
            isr = new InputStreamReader(in,"utf-8");
            br = new BufferedReader(isr);
            for (String header : headerInfo){
                bw.write(header);
                bw.newLine();
                if (header.contains("package")){ bw.newLine();}
            }
            String line = null;
            while((line=br.readLine())!=null){
                bw.write(line);
                bw.newLine();
            }
            bw.flush();

        }catch (Exception e){
            logger.error("生成基础类：{}失败",fileName,e);
        }finally {
            if (br != null){
                try {
                    br.close();
                }catch (Exception e){e.printStackTrace();}
            }
            if (isr != null){
                try {
                    isr.close();
                }catch (Exception e){e.printStackTrace();}
            }
            if (in != null){
                try {
                    in.close();
                }catch (Exception e){e.printStackTrace();}
            }
            if (bw != null){
                try {
                    bw.close();
                }catch (Exception e){e.printStackTrace();}
            }
            if (osw != null){
                try {
                    osw.close();
                }catch (Exception e){e.printStackTrace();}
            }
            if (out != null){
                try {
                    out.close();
                }catch (Exception e){e.printStackTrace();}
            }
        }
    }
}
