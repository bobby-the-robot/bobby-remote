package bobby.remote.converter;

import bobby.remote.dto.MotionDto;
import bobby.remote.dto.MotionDto.DirectionDto;
import bobby.remote.model.Motion;
import bobby.remote.model.Motion.Direction;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MotionConverter implements Converter<MotionDto, Motion> {

    @Override
    public Motion convert(MotionDto source) {
        DirectionDto direction = source.getDirection();
        return new Motion(Direction.valueOf(direction.name()));
    }
}
