package io;

import java.io.*;

public class DataStreamDemo {
    public static void out() throws IOException {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("hello.dat"))) {
            dos.writeChars("你好");
        }
    }

    public static void in() throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("hello.dat"))) {
            byte b[] = new byte[1000];
            dis.readFully(b);
            System.out.println(new String(b));
        }
    }
    public static void main(String[] args) throws IOException {
        in();
    }
}
