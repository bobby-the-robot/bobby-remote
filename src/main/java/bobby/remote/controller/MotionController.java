package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import bobby.remote.dto.MotionDto;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MotionController {

    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/move")
    public void move(@RequestBody MotionDto motionDto) {
        System.out.println(motionDto);
        messagingTemplate.convertAndSend("/topic/motion", motionDto.getDirection().toString());
    }

/*    @PostMapping("/move")
    @SendTo("/topic/motion")
    public MotionDto move(@RequestBody MotionDto motionDto) {
        System.out.println(motionDto);
        return motionDto;
    }*/
}
