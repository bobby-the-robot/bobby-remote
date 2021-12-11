package bobby.remote.controller

import bobby.remote.amqp.MessageSender
import bobby.remote.dto.MotionDto
import spock.lang.Specification
import spock.lang.Subject

import static bobby.remote.dto.MotionDto.Direction

class MotionControllerSpec extends Specification {

    private MessageSender messageSender = Mock MessageSender

    @Subject
    private MotionController motionController = new MotionController(messageSender)

    def "should invoke message sender"() {
        given:
        MotionDto motionDto = new MotionDto(direction)

        when:
        motionController.move(motionDto)

        then:
        1 * messageSender.send(motionDto)
        0 * _

        where:
        direction           | _
        Direction.FORWARD   | _
        Direction.BACK      | _
        Direction.RIGHT     | _
        Direction.LEFT      | _
        Direction.STOP      | _
    }
}
