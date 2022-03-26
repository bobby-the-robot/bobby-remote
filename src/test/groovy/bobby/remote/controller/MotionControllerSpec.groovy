package bobby.remote.controller

import bobby.remote.dto.MotionDto
import bobby.remote.dto.MotionDto.DirectionDto
import bobby.remote.model.Motion
import bobby.remote.model.Motion.Direction
import org.springframework.core.convert.ConversionService
import spock.lang.Specification
import spock.lang.Subject

class MotionControllerSpec extends Specification {

    private ConversionService conversionService = Mock ConversionService

    @Subject
    private MotionController motionController = new MotionController(conversionService)

    def "should invoke message sender"() {
        given:
        MotionDto motionDto = new MotionDto(direction)
        Motion motion = new Motion(Direction.valueOf(direction.name()))
        Motion expected = new Motion(Direction.valueOf(direction.name()))

        when:
        Motion actual = motionController.move(motionDto)

        then:
        1 * conversionService.convert(motionDto, Motion) >> motion
        0 * _

        and:
        actual == expected

        where:
        direction               | _
        DirectionDto.FORWARD    | _
        DirectionDto.BACK       | _
        DirectionDto.RIGHT      | _
        DirectionDto.LEFT       | _
        DirectionDto.STOP       | _
    }
}
