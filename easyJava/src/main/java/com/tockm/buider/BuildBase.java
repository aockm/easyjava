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
