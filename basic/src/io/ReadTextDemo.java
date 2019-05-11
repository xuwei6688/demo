package io;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ReadTextDemo {
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");

    /**
     * 比较传统的读取方式
     * @throws IOException
     */
    public static void readTxt() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("printWriterA.txt")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        DataOutput out = new DataOutputStream(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\nihao.dat"));
        out.writeInt(3);
        out.writeDouble(4.5);
        out.writeBoolean(true);
        out.writeChar('u');

        DataInput in = new DataInputStream(new FileInputStream("C:\\Users\\Administrator\\Desktop\\nihao.dat"));
        System.out.println(in.readInt());
        System.out.println(in.readDouble());
        System.out.println(in.readBoolean());
        System.out.println(in.readChar());
    }
}
