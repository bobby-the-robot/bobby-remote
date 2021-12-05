package bobby.remote.configuration;

public abstract class Constants {

    public static final String MOTION_CONTROL_QUEUE_NAME = "motion.control";

    public static final String AMQP_URI = System.getenv("CLOUDAMQP_URL");
}
