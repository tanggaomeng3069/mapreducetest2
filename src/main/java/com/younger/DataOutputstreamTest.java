package com.younger;

import java.io.*;

public class DataOutputstreamTest {
    public static void main(String[] args) throws IOException {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("F:\\codedata\\mapreducetest2\\a.dat"));
        dos.write("中国，你好！".getBytes("utf-8"));
        dos.close();


        DataOutputStream dos2 = new DataOutputStream(new FileOutputStream("F:\\codedata\\mapreducetest2\\b.dat"));
        dos2.writeUTF("中国，你好");
        dos2.close();

    }
}
