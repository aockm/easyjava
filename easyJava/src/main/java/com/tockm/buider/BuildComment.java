package com.tockm.buider;

import com.tockm.utils.DateUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Date;

public class BuildComment {
    public static void createClassComment(BufferedWriter bw, String classComment) throws IOException {
        bw.write("/**\n");
        bw.write(" *@Description: " + classComment + "\n");
        bw.write(" *@date: "+ DateUtils.format(new Date(), DateUtils._YYYYMMDD) + "\n");
        bw.write(" */\n");
    }
    public static void createFieldComment(BufferedWriter bw, String fieldComment) throws IOException {
        bw.write("\t/**\n");
        bw.write("\t * "+fieldComment + "\n");
        bw.write("\t */\n");
    }
    public static void createMethodComment(BufferedWriter bw, String methodComment) throws IOException {
        bw.write("\t/**\n");
        bw.write("\t * "+methodComment + "\n");
        bw.write("\t */\n");
    }
}
