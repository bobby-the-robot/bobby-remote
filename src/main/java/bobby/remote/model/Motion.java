package bobby.remote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motion {

    private Direction direction;

    public enum Direction {
        FORWARD, LEFT, RIGHT, BACK, STOP
    }
}
