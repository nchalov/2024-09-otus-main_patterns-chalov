package command.rotate;

import common.Command;
import domain.rotate.RotatingObject;
import command.rotate.exception.RotateCommandException;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class RotateCommand implements Command {

    private final RotatingObject rotatingObject;

    @Override
    public void handle() {
        if (canHandle()) {
            rotatingObject.setAngle(rotatingObject.getAngle().plus(rotatingObject.getAngularVelocity()));
        } else {
            throw new RotateCommandException("Attempt to rotate object has been failed");
        }
    }

    @Override
    public boolean canHandle() {
        return rotatingObject.canRotate()
                && Objects.nonNull(rotatingObject.getAngle())
                && Objects.nonNull(rotatingObject.getAngularVelocity());
    }

}
