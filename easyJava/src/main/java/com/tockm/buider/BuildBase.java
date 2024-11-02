package com.tockm.buider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class BuildBase {

    private static final Logger logger = LoggerFactory.getLogger(BuildBase.class);
    public static void execute(){}

    public static void build(String fileName, String outPutPath) {
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
            String templatePath = BuildBase.class.getClassLoader().getResource("/template"+fileName+".txt").getPath();
            in = new FileInputStream(fileName);
            isr = new InputStreamReader(in);
            br = new BufferedReader(isr);


        }catch (Exception e){
            logger.error("生成基础类：{}失败",fileName);
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
