package threadNetworking;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <pre>
 * threadNetworking 
 * RabbitMQ.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 9.
 * @author : yhyeon
 * @version : v1.0
 */

public class RabbitMQ {
	
	private final static String QUEUE_NAME = "hello";

	
	public static void main(String[] args) {
		
		ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
            for (int i = 0; i <= 100000; i++) {
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Hello World!" + (int) (Math.random() * 100);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Set '" + message + "'");
                Thread.sleep(10);
            }
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
