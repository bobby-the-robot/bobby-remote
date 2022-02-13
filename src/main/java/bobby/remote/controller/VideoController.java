package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class VideoController {

    @PostMapping("/frames")
    @SendTo("/topic/frames")
    public String stream(@RequestBody byte[] frame) {
        return Base64.getEncoder()
                .encodeToString(frame);
    }
}
