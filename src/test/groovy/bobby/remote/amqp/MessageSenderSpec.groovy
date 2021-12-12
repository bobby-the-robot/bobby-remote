package bobby.remote.amqp

import bobby.remote.amqp.rabbitmq.RabbitMQMessageSender
import bobby.remote.model.Motion
import bobby.remote.model.Motion.Direction
import com.rabbitmq.client.Channel
import com.rabbitmq.client.MessageProperties
import spock.lang.Specification
import spock.lang.Subject

import static bobby.remote.configuration.Constants.MOTION_CONTROL_QUEUE_NAME

class MessageSenderSpec extends Specification {

    private Channel rabbitMQChannel = Mock Channel
    @Subject
    private MessageSender messageSender = new RabbitMQMessageSender(rabbitMQChannel)

    def 'should drop message into the queue'() {
        given:
        Motion motion = new Motion(direction)
        byte[] payload = direction.name().bytes

        when:
        messageSender.send(motion)

        then:
        1 * rabbitMQChannel.basicPublish("", MOTION_CONTROL_QUEUE_NAME, MessageProperties.TEXT_PLAIN, payload)
        0 * _

        where:
        direction           | _
        Direction.FORWARD   | _
        Direction.RIGHT     | _
        Direction.LEFT      | _
        Direction.BACK      | _
        Direction.STOP      | _
    }
}
