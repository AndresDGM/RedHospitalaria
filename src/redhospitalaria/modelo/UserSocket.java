/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package redhospitalaria.modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;


public class UserSocket {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket socket;
    public static final String host = /*"192.168.14.191"*/ "localhost";
    public static final int port = 8001;

    public UserSocket(String host, int port) throws IOException, ConnectException{
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream()); 
            in = new ObjectInputStream(socket.getInputStream());
    }
    
    public UserSocket(Socket socket) throws IOException, ConnectException{
            this.socket = socket;
            out = new ObjectOutputStream(socket.getOutputStream()); 
            in = new ObjectInputStream(socket.getInputStream());
    }
    
    public ObjectOutputStream getOut() {
        return out;
    }

    public ObjectInputStream getIn() {
        return in;
    }
    
    public void close() throws IOException{
        socket.close();
    }
}
