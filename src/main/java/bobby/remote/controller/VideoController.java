package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class VideoController {

/*    @MessageMapping("/client")
    @SendTo("/topic/frames")
    public String stream(@RequestBody String payload) {
        System.out.println(new Date());
        return payload;
    }*/

    @MessageMapping("/client")
    @SendTo("/topic/frames")
    public String stream(@RequestBody byte[] bytes) {
        System.out.println(new Date());
        return Base64.getEncoder().encodeToString(bytes);
    }
}
