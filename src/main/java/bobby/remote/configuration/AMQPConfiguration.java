package bobby.remote.configuration;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;

import static bobby.remote.configuration.Constants.MOTION_CONTROL_QUEUE_NAME;

@Configuration
public class AMQPConfiguration {

    private static final String MESSAGE_TTL_KEY = "x-message-ttl";
    private static final int MESSAGE_TTL_VALUE = 3000;
    private static final Map<String, Object> QUEUE_ARGS = Map.of(MESSAGE_TTL_KEY, MESSAGE_TTL_VALUE);


    @Bean
    @SneakyThrows
    @Profile("!integration-test")
    public Channel channel(@Value("${amqp.uri}") String amqpUri) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(amqpUri);

        Channel channel = factory.newConnection()
                .createChannel();
        channel.queueDeclare(MOTION_CONTROL_QUEUE_NAME, false, false, false, QUEUE_ARGS);

        return channel;
    }
}
