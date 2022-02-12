package bobby.remote.controller;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequiredArgsConstructor
public class VideoControllerBak {

    private final Channel channel;
    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/frame")
    public void receive(@RequestBody byte[] bytes) {
        String payload = Base64.getEncoder().encodeToString(bytes);
        messagingTemplate.convertAndSend("/topic/messages", payload);
    }
}
