package threadNetworking;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

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
	ConnectNodeJsQ cnQ = new ConnectNodeJsQ();
	PublicArr pa = new PublicArr();
		
	Socket SS;
	String ID;
	String getID;
	int class4 = 25;
	
	userThread(Socket SS) {
		this.SS = SS;
	}
	
	public String Checking(String[] check) {
		String notConnectStudents = "";
		for (int i = 0; i < check.length; i++) {
			if(check[i] == null) notConnectStudents = notConnectStudents + (i + 1) + " ";
		}
		
		return notConnectStudents;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				InputStream IS = SS.getInputStream();
				byte[] bt = new byte[256];
				int size = IS.read(bt);

				String output = new String(bt, 0, size, "UTF-8");
				if (output.substring(output.length() - 1).equals("0")) {
					ID = output.substring(0, output.length() - 1);
					int index = ID.indexOf("2");
					getID = ID.substring(index + 3, index + 5);
//					pa.check.add(getID);
					pa.checking[Integer.parseInt(getID) - 1] = getID;
				} else {
					System.out.println(ID + "> " + output);
//                	cnj.conn(ID, output);
//					pa.check.sort(Comparator.naturalOrder());
					String id = Checking(pa.checking);
					cnQ.connectionQ(ID, output, id);
					
				}
			}
		} catch (IOException | TimeoutException e) {
//			pa.check.remove(getID);
			pa.checking[Integer.parseInt(getID)] = null;
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
	
	ArrayList<String> check = new ArrayList<String>();

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ServerSocket MSS = null;

		try {
			MSS = new ServerSocket();
			MSS.bind(new InetSocketAddress(InetAddress.getLocalHost(), 30001));

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
