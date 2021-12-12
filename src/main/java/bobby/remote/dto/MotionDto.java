package bobby.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotionDto {

    private DirectionDto direction;

    public enum DirectionDto {
        FORWARD, LEFT, RIGHT, BACK, STOP
    }
}
