package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MotionController {

    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/move")
    public void move(@RequestBody String direction) {
        System.out.println(direction);
        messagingTemplate.convertAndSend("/topic/motion", direction);
    }
}
