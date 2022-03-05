package bobby.remote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;

@Controller
@RequiredArgsConstructor
public class VideoController {

    private final SimpMessageSendingOperations messagingTemplate;

/*    @PostMapping("/frames")
    public void receive(@RequestBody byte[] bytes) {
        String payload = Base64.getEncoder().encodeToString(bytes);
        messagingTemplate.convertAndSend("/topic/frames", payload);
    }*/

    @MessageMapping("/client")
    @SendTo("/topic/frames")
    @GetMapping("/frames")
    //public byte[] stream(@RequestBody byte[] bytes) {
    //public String stream(@RequestBody String payload) {
    //public void stream(@RequestBody String payload) {
    public void stream(@RequestBody byte[] bytes) {
        String payload = Base64.getEncoder().encodeToString(bytes);
        System.out.println(payload);
        messagingTemplate.convertAndSend("/topic/frames", payload);
    }
}
