package bobby.remote.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import bobby.remote.dto.MotionDto;

@Slf4j
@Controller
public class DirectionController {

    @SneakyThrows
    @MessageMapping("/server/direction")
    @SendTo("/client/direction")
    public MotionDto direction(MotionDto message) {
        log.info(message.toString());
        return message;
    }
}
