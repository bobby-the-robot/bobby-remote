package bobby.remote.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles('integration-test')
class MotionControllerIntegrationSpec extends Specification {

    @Autowired
    private MockMvc mvc

    /*def 'should send motion direction to message queue'() {
        given:
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

        where:
        direction   | _
        'FORWARD'   | _
        'RIGHT'     | _
        'LEFT'      | _
        'BACK'      | _
        'STOP'      | _
    }*/
}
