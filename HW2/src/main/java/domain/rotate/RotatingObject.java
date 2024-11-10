package domain.rotate;

public interface RotatingObject {

    Angle getAngle();

    Angle getAngularVelocity();

    void setAngle(Angle angle);

    boolean canRotate();

}
