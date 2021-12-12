package bobby.remote.amqp.rabbitmq;

import bobby.remote.amqp.MessageSender;
import bobby.remote.model.Motion;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static bobby.remote.configuration.Constants.MOTION_CONTROL_QUEUE_NAME;

@Component
@RequiredArgsConstructor
public class RabbitMQMessageSender implements MessageSender {

    private final Channel channel;

    @Override
    @SneakyThrows
    public void send(Motion motion) {
        byte[] payload = motion.getDirection()
                .name()
                .getBytes(StandardCharsets.UTF_8);
        channel.basicPublish("", MOTION_CONTROL_QUEUE_NAME, MessageProperties.TEXT_PLAIN, payload);
    }
}
