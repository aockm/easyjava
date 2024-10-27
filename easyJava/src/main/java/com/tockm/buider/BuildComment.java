package com.tockm.buider;

import java.io.BufferedWriter;
import java.io.IOException;

public class BuildComment {
    public static void createClassComment(BufferedWriter bw, String classComment) throws IOException {
        bw.write("/**\n");
        bw.write(" *@Description: " + classComment + "\n");
        bw.write(" *@Date:"+classComment + "\n");
        bw.write(" */\n");
    }
}
