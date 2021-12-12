package bobby.remote.controller;

import bobby.remote.amqp.MessageSender;
import bobby.remote.model.Motion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import bobby.remote.dto.MotionDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MotionController {

    private final ConversionService conversionService;
    private final MessageSender messageSender;

    @PostMapping("/move")
    public void move(@RequestBody MotionDto motionDto) {
        log.info(motionDto.toString());
        Motion motion = conversionService.convert(motionDto, Motion.class);
        messageSender.send(motion);
    }
}
