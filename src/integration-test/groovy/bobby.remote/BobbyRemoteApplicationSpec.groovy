package bobby.remote

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles('integration')
class BobbyRemoteApplicationSpec extends Specification{

    @Autowired
    ApplicationContext context

    def "test context loads"() {
        expect:
        true
        context != null
    }
}
