package bobby.remote.controller

import com.rabbitmq.client.Channel
import com.rabbitmq.client.MessageProperties
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static bobby.remote.configuration.Constants.MOTION_CONTROL_QUEUE_NAME
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles('integration-test')
class MotionControllerIntegrationSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @SpringBean
    private Channel amqpChannel = Mock Channel

    def 'should send motion direction to message queue'() {
        given:
        String direction = 'FORWARD'
        byte[] directionBytes = direction.bytes
        String directionPayload = "{\"direction\": \"$direction\"}"

        when:
        mvc.perform(post("/move").accept(APPLICATION_JSON_VALUE)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(directionPayload))
                .andExpect(status().isOk())

        then:
        1 * amqpChannel.basicPublish(
                '', MOTION_CONTROL_QUEUE_NAME, MessageProperties.TEXT_PLAIN, directionBytes)
        0 * _
    }

    def 'should return bad request status'() {
        given:
        String direction = 'UNKNOWN'
        String directionPayload = '{"direction": "UNKNOWN"}'

        when:
        mvc.perform(post("/move").accept(APPLICATION_JSON_VALUE)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(directionPayload))
                .andExpect(status().isBadRequest())

        then:
        0 * _
    }
}
