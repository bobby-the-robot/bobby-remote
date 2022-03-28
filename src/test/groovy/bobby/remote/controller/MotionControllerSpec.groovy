package bobby.remote.controller

import org.springframework.messaging.simp.SimpMessageSendingOperations
import spock.lang.Specification
import spock.lang.Subject

class MotionControllerSpec extends Specification {

    SimpMessageSendingOperations messagingTemplate = Mock SimpMessageSendingOperations

    @Subject
    private MotionController motionController = new MotionController(messagingTemplate)

    def "should invoke messaging template"() {
        when:
        motionController.move(direction)

        then:
        1 * messagingTemplate.convertAndSend('/topic/motion', direction)
        0 * _

        where:
        direction    | _
        'FORWARD'    | _
        'BACK'       | _
        'RIGHT'      | _
        'LEFT'       | _
        'STOP'       | _
    }
}
