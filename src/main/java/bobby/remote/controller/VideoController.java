package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class VideoController {

    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/frames")
    public void receive(@RequestBody byte[] bytes) {
        messagingTemplate.convertAndSend("/topic/frames", bytes);
    }
}
