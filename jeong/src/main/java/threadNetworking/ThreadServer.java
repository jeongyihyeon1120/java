package threadNetworking; 

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * <pre>
 * threadNetworking 
 * ThreadClient.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 7. 8.
 * @author : yhyeon
 * @version : v1.0
 */

class userThread extends Thread {
	
	ConnectNodeJs cnj = new ConnectNodeJs();
 
    Socket SS;
    String ID;
 
    userThread(Socket SS) {
        this.SS = SS;
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                InputStream IS = SS.getInputStream();
                byte[] bt = new byte[256];
                int size = IS.read(bt);
 
                String output = new String(bt, 0, size, "UTF-8");
                if(output.substring(output.length() - 1).equals("0")) {
                	ID = output.substring(0, output.length() - 1);
                }else {
                	if(output.equals("Yes")) {   		
                		System.out.println(ID + "> " + output);	
                		cnj.conn(ID, output);
                	}
				}
            }
        } catch (IOException e) {
            System.out.println("--" + ID + " user OUT");
        }
    }
 
}//
 
class connectThread extends Thread {
 
    ServerSocket MSS;
    int Count = 1;
 
    connectThread(ServerSocket MSS) {
        this.MSS = MSS;
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                Socket SS = MSS.accept();
                
                userThread ust = new userThread(SS);
                ust.start();
                Count++;
            }
 
        } catch (IOException e) {
            System.out.println("--SERVER CLOSE");
        }
    }
}//
 
public class ThreadServer {
 
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ServerSocket MSS = null;
 
        try {
            MSS = new ServerSocket();
            MSS.bind(new InetSocketAddress(InetAddress.getLocalHost(), 4040));
 
            System.out.println("--SERVER Close : input num");
            System.out.println("--SERVER Waiting...");
            connectThread cnt = new connectThread(MSS);
            cnt.start();
            int temp = input.nextInt();
 
        } catch (Exception e) {
            System.out.println(e);
        }
 
        try {
            MSS.close();
        } catch (Exception e) {
            System.out.println(e);
        }
 
    }// MAIN
}
