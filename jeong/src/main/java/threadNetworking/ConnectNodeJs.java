package threadNetworking;

import io.socket.client.IO;
import io.socket.client.Socket;


/**
 * <pre>
 * threadNetworking 
 * ConnectNodeJs.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 7. 11.
 * @author : yhyeon
 * @version : v1.0
 */

public class ConnectNodeJs {
	
    static Socket socket;
	
	public void conn(String id, String status) {
		
		try {
			socket = IO.socket("http://localhost:8080");
			
			socket.on(Socket.EVENT_CONNECT, (Object... objects) -> {
				socket.emit("joinRoom", id, status);
			});
			socket.connect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
