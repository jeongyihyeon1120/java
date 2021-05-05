package threadNetworking;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * <pre>
 * threadNetworking 
 * ConnectNodeJsQ.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 9.
 * @author : yhyeon
 * @version : v1.0
 */

public class ConnectNodeJsQ {

	private final String QUEUE_NAME = "hello";
	
	public void connectionQ(String id, String status, String check) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = id + "/" + status + "/" + check;
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Set '" + message + "'");
			Thread.sleep(10);
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
