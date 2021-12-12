package bobby.remote.amqp;

import bobby.remote.model.Motion;

public interface MessageSender {

    void send(Motion motion);
}
