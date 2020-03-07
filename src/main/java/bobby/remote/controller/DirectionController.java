package bobby.remote.controller;

import lombok.SneakyThrows;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import bobby.remote.dto.MotionDto;

@Controller
public class DirectionController {

    @SneakyThrows
    @MessageMapping("/server/direction")
    @SendTo("/client/direction")
    public MotionDto direction(MotionDto message) {
        System.out.println(message);
        return message;
    }
}
