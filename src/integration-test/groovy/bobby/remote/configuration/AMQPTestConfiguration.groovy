package bobby.remote.configuration

import com.rabbitmq.client.Channel
import groovy.transform.AutoImplement
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class AMQPTestConfiguration {

    @Bean
    @Primary
    public Channel channel() {
        new MockChannel()
    }

    @AutoImplement
    static class MockChannel implements Channel {

    }
}
