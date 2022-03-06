package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class VideoController {

    @MessageMapping("/client")
    @SendTo("/topic/frames")
    public String stream(@RequestBody String payload) {
        return payload;
    }
}
