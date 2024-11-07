package command.move;

import common.Command;
import domain.move.MovingObject;
import command.move.exception.MoveCommandException;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class MoveCommand implements Command {

    private final MovingObject movingObject;

    @Override
    public void handle() {
        if (canHandle()) {
            movingObject.setPosition(movingObject.getPosition().plus(movingObject.getVelocity()));
        } else {
            throw new MoveCommandException("Attempt to move object has been failed");
        }
    }

    @Override
    public boolean canHandle() {
        return movingObject.canMove()
                && Objects.nonNull(movingObject.getPosition())
                && Objects.nonNull(movingObject.getVelocity());
    }

}
