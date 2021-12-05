package bobby.remote.amqp;

import bobby.remote.dto.MotionDto;

public interface MessageSender {

    void send(MotionDto motionDto);
}
