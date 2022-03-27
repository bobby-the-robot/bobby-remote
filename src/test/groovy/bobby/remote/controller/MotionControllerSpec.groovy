package bobby.remote.controller

import bobby.remote.dto.MotionDto
import bobby.remote.dto.MotionDto.DirectionDto
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Subject

class MotionControllerSpec extends Specification {

    @Subject
    private MotionController motionController = new MotionController()

    @Ignore
    def "should invoke message sender"() {
        given:
        MotionDto motionDto = new MotionDto(direction)
        MotionDto expected = new MotionDto(direction)

        when:
        MotionDto actual = motionController.move(motionDto)

        then:
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
