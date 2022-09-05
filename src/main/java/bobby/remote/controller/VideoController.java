package bobby.remote.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;

@Controller
public class VideoController {

    @MessageMapping("/video")
    @SendTo("/topic/frames")
    public String stream(@RequestBody String payload) {
        return payload;
    }

    @MessageMapping("/video-bytes")
    @SendTo("/topic/frames")
    public String stream(@RequestBody byte[] bytes) {
        String payload = Base64.getEncoder().encodeToString(bytes);
        System.out.println(payload);
        return payload;
    }
}
