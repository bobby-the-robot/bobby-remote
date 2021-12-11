package bobby.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotionDto {

    private Direction direction;

    public enum Direction {
        FORWARD, LEFT, RIGHT, BACK, STOP
    }
}
