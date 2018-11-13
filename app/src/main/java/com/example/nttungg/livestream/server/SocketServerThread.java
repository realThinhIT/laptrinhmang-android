package com.example.nttungg.livestream.server;

import android.util.Log;

import com.example.nttungg.livestream.models.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;

/**
 * Created by nttungg on 11/13/18.
 */

public class SocketServerThread extends Thread{
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void run(){

        try{
            Socket socket = new Socket("172.20.10.4",9999);
            // Client established connection.
            // Create input and output streams
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            out.writeObject("AAAA");

        } catch(Exception e) {
            // Omitting exception handling for clarity
        }
    }

}
