package bobby.remote.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles('integration')
class MotionControllerIntegrationSpec extends Specification {

    @Autowired
    private MockMvc mvc

    def "should send motion direction to message queue"() {
        given:
        String directionPayload = '{"direction": "FORWARD"}'

        expect:
        mvc.perform(post("/move").accept(APPLICATION_JSON_VALUE)
                    .contentType(APPLICATION_JSON_VALUE)
                    .content(directionPayload))
                .andExpect(status().isOk())

        //and:
        //check payload
    }
}
