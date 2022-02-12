package bobby.remote.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

import static bobby.remote.configuration.Constants.VIDEO_STREAMING_QUEUE_NAME;

@Controller
@RequiredArgsConstructor
public class VideoController {

    private static final byte[] TWO_LINE_BYTES = "\r\n\r\n".getBytes();

    private final Channel channel;

    /*@SneakyThrows
    @GetMapping("/stream")
    public void receive() {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            byte[] payloadBytes = delivery.getBody();
            String payloadBase64 = Base64.getEncoder().encodeToString(payloadBytes);
            messagingTemplate.convertAndSend("/topic/messages", payloadBase64);
        };

        channel.basicConsume(VIDEO_STREAMING_QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }*/

    @SneakyThrows
    @GetMapping("/stream")
    public void stream(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("multipart/x-mixed-replace; boundary=--BoundaryString");
        OutputStream outputStream = response.getOutputStream();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            byte[] payloadBytes = delivery.getBody();
            // write the image and wrapper
            outputStream.write((
                    "--BoundaryString\r\n" +
                            "Content-type: image/jpeg\r\n" +
                            "Content-Length: " +
                            payloadBytes.length +
                            "\r\n\r\n").getBytes());
            outputStream.write(payloadBytes);
            outputStream.write(TWO_LINE_BYTES);
            outputStream.flush();
        };

        channel.basicConsume(VIDEO_STREAMING_QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
