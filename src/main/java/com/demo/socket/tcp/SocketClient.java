package com.demo.socket.tcp;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws  IOException{
        Socket socket = new Socket("localhost",  8888);

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            writer.write("哈哈哈");
            writer.flush();
            writer.close();
            socket.close();
        } finally {

        }

    }
}
