package bobby.remote.configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static bobby.remote.configuration.Constants.AMQP_URI;
import static bobby.remote.configuration.Constants.MOTION_CONTROL_QUEUE_NAME;

@Configuration
public class AMQPConfiguration {

    @Bean
    @SneakyThrows
    public Channel channel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(AMQP_URI);

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 3000);
        channel.queueDeclare(MOTION_CONTROL_QUEUE_NAME, false, false, false, null);

        return channel;
    }
}
