package bobby.remote.dto;

import lombok.Data;

@Data
public class MotionDto {

    private final Direction direction;

    public enum Direction {
        FORWARD, LEFT, RIGHT, BACK, STOP
    }
}
