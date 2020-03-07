package bobby.remote.dto;

import lombok.Data;

@Data
public class MotionDto {

    private Direction direction;

    public enum Direction {
        FORWARD, LEFT, RIGHT, BACK
    }
}
