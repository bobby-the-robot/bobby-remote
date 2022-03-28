package bobby.remote.controller

import spock.lang.Specification
import spock.lang.Subject

class VideoControllerSpec extends Specification {

    @Subject
    private VideoController videoController = new VideoController()

    def "should return the incoming payload"() {
        given:
        String payload = 'a base64 string'
        String expected = 'a base64 string'

        when:
        String actual = videoController.stream(payload)

        then:
        actual == expected

        and:
        0 * _
    }
}
