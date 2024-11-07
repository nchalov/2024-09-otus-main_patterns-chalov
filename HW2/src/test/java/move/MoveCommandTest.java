package move;

import command.move.MoveCommand;
import domain.move.MovingObject;
import command.move.exception.MoveCommandException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.move.Vector;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MoveCommandTest {

    @Mock
    private MovingObject movingObject;

    private MoveCommand moveCommand;

    @BeforeEach
    public void setUp() {
        moveCommand = new MoveCommand(movingObject);
    }

    @AfterEach
    public void resetMocks() {
        reset(movingObject);
    }

    @Test
    public void shouldMoveCommandToBeSuccess() {
        when(movingObject.getPosition()).thenReturn(Vector.of(new Integer[]{12, 5}));
        when(movingObject.getVelocity()).thenReturn(Vector.of(new Integer[]{-7, 3}));
        when(movingObject.canMove()).thenReturn(true);

        moveCommand.handle();

        verify(movingObject).setPosition(Vector.of(new Integer[]{5, 8}));
    }

    @Test
    public void shouldMoveCommandToBeFailWhenPositionIsNull() {
        when(movingObject.getPosition()).thenReturn(null);
        assertThrows(MoveCommandException.class, moveCommand::handle);
    }

    @Test
    public void shouldMoveCommandToBeFailWhenVelocityIsNull() {
        when(movingObject.getVelocity()).thenReturn(null);
        assertThrows(MoveCommandException.class, moveCommand::handle);
    }

    @Test
    public void shouldMoveCommandToBeFailWhenCanMoveIsFalse() {
        when(movingObject.canMove()).thenReturn(false);
        assertThrows(MoveCommandException.class, moveCommand::handle);
    }

}
