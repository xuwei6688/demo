package io.randomAccess;

import java.io.*;

public class DataIO {

    /**
     * 写出从字符串开头指定数量的码元（如果码元过少，用0填充）
     * @param s
     * @param size 字符数量
     * @param out
     */
    public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) {
                ch = s.charAt(i);
            }
            out.writeChar(ch);
        }
    }

    /**
     * 从输入流读取字符，读取size个码元，遇到'0'停止
     * @param size
     * @param in
     */
    public static String  readFixedString(int size, DataInput in) throws IOException {
        int i = 0;
        boolean more = true;
        StringBuilder sb = new StringBuilder();
        while (more && i < size) {
            char ch = in.readChar();
            i++;
            if (ch == '0') {
                more = false;
            } else {
                sb.append(ch);
            }
        }
        in.skipBytes(2 * (size - i));
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        writeFixedString("linghuchong", 80, new DataOutputStream(new FileOutputStream("nihao.dat")));
        String s = readFixedString(80, new DataInputStream(new FileInputStream("nihao.dat")));
        System.out.println(s);
    }
}
