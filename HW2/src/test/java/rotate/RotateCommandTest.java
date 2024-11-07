package rotate;

import command.rotate.RotateCommand;
import domain.rotate.RotatingObject;
import command.rotate.exception.RotateCommandException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import domain.rotate.Angle;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RotateCommandTest {

    @Mock
    private RotatingObject rotatingObject;

    private RotateCommand rotateCommand;

    @BeforeEach
    public void setUp() {
        rotateCommand = new RotateCommand(rotatingObject);
    }

    @AfterEach
    public void resetMocks() {
        reset(rotatingObject);
    }

    @Test
    public void shouldRotateCommandToBeSuccess() {
        var angle1 = Angle.of(2.2);
        var angle2 = Angle.of(1.1);
        var result = angle1.plus(angle2);

        when(rotatingObject.getAngle()).thenReturn(angle1);
        when(rotatingObject.getAngularVelocity()).thenReturn(angle2);
        when(rotatingObject.canRotate()).thenReturn(true);

        rotateCommand.handle();

        verify(rotatingObject).setAngle(result);
    }

    @Test
    public void shouldRotateCommandToBeFailWhenAngleIsNull() {
        when(rotatingObject.getAngle()).thenReturn(null);
        assertThrows(RotateCommandException.class, rotateCommand::handle);
    }

    @Test
    public void shouldRotateCommandToBeFailWhenAngularVelocityIsNull() {
        when(rotatingObject.getAngularVelocity()).thenReturn(null);
        assertThrows(RotateCommandException.class, rotateCommand::handle);
    }

    @Test
    public void shouldRotateCommandToBeFailWhenCanRotateIsFalse() {
        when(rotatingObject.canRotate()).thenReturn(false);
        assertThrows(RotateCommandException.class, rotateCommand::handle);
    }

}
