package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * PrinterWriter默认需要手动刷新
 */
public class PrintWriterDemo {
    public static void printTxt() throws FileNotFoundException, UnsupportedEncodingException {
        try(PrintWriter printWriter = new PrintWriter("printWriterA.txt", "UTF-8");){
            printWriter.println("你好");
            printWriter.println(56);
        }
    }

    public static void main(String[] args) throws Exception {
        printTxt();
        System.out.println(System.getProperty("user.dir"));
    }
}
