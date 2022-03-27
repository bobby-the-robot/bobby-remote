package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import bobby.remote.dto.MotionDto;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MotionController {

    @PostMapping("/move")
    @SendTo("/topic/motion")
    public MotionDto move(@RequestBody MotionDto motionDto) {
        return motionDto;
    }
}
