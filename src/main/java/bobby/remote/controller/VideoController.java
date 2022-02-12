package bobby.remote.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

import static bobby.remote.configuration.Constants.VIDEO_STREAMING_QUEUE_NAME;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final Channel channel;
    private final SimpMessageSendingOperations messagingTemplate;

    @SneakyThrows
    @GetMapping("/stream")
    public void receive() {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            byte[] payloadBytes = delivery.getBody();
            String payloadBase64 = Base64.getEncoder().encodeToString(payloadBytes);
            messagingTemplate.convertAndSend("/topic/messages", payloadBase64);
        };

        channel.basicConsume(VIDEO_STREAMING_QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
