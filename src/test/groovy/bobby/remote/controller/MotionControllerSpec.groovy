package bobby.remote.controller

import bobby.remote.amqp.MessageSender
import bobby.remote.dto.MotionDto
import bobby.remote.dto.MotionDto.DirectionDto
import bobby.remote.model.Motion
import bobby.remote.model.Motion.Direction
import org.springframework.core.convert.ConversionService
import spock.lang.Specification
import spock.lang.Subject

class MotionControllerSpec extends Specification {

    private ConversionService conversionService = Mock ConversionService
    private MessageSender messageSender = Mock MessageSender

    @Subject
    private MotionController motionController = new MotionController(conversionService, messageSender)

    def "should invoke message sender"() {
        given:
        MotionDto motionDto = new MotionDto(direction)
        Motion motion = new Motion(Direction.valueOf(direction.name()))

        when:
        motionController.move(motionDto)

        then:
        1 * conversionService.convert(motionDto, Motion) >> motion
        1 * messageSender.send(motion)
        0 * _

        where:
        direction               | _
        DirectionDto.FORWARD    | _
        DirectionDto.BACK       | _
        DirectionDto.RIGHT      | _
        DirectionDto.LEFT       | _
        DirectionDto.STOP       | _
    }
}
