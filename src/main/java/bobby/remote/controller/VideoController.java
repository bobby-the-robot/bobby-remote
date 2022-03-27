package bobby.remote.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class VideoController {

    @MessageMapping("/video")
    @SendTo("/topic/frames")
    public String stream(@RequestBody String payload) {
        return payload;
    }
}
