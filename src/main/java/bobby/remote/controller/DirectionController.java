package bobby.remote.controller;

import bobby.remote.amqp.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import bobby.remote.dto.MotionDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DirectionController {

    private final MessageSender messageSender;

    @PostMapping("/direction")
    public void direction(@RequestBody MotionDto message) {
        log.info(message.toString());
        messageSender.send(message);
    }
}
