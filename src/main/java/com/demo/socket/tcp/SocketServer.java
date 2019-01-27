package com.demo.socket.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);

        Socket socket = serverSocket.accept();

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String clientData = reader.readLine();
        System.out.println(clientData);

        serverSocket.close();
    }
}
