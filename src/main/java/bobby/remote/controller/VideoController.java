package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class VideoController {

    //private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/frames")
    @SendTo("/topic/frames")
    public String stream(@RequestBody byte[] frame) {
        return Base64.getEncoder()
                .encodeToString(frame);
    }

/*    @PostMapping("/frames")
    public void receive(@RequestBody byte[] bytes) {
        String payload = Base64.getEncoder().encodeToString(bytes);
        messagingTemplate.convertAndSend("/topic/frames", payload);
    }*/
}
