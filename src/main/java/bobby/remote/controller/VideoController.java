package bobby.remote.controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.GetResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;

import static bobby.remote.configuration.Constants.VIDEO_STREAMING_QUEUE_NAME;

@Controller
@RequiredArgsConstructor
public class VideoController {

    private static final byte[] TWO_LINE_BYTES = "\r\n\r\n".getBytes();

    private final Channel channel;

    @SneakyThrows
    @GetMapping("/stream")
    protected void stream(HttpServletResponse response) {
        response.setContentType("multipart/x-mixed-replace; boundary=--BoundaryString");
        OutputStream outputStream = response.getOutputStream();

        while(true) {
            GetResponse payload = channel.basicGet(VIDEO_STREAMING_QUEUE_NAME, true);

            if (payload == null) {
                Thread.sleep(10);
            } else {
                System.out.println(new Date());
                byte[] body = payload.getBody();
                outputStream.write((
                        "--BoundaryString\r\n" +
                                "Content-type: image/jpeg\r\n" +
                                "Content-Length: " +
                                body.length +
                                "\r\n\r\n").getBytes());
                outputStream.write(body);
                outputStream.write(TWO_LINE_BYTES);
                //outputStream.flush();
            }
        }
    }
}
