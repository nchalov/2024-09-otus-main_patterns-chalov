package domain.move;

public interface MovingObject {

    Vector getPosition();

    Vector getVelocity();

    void setPosition(Vector position);

    boolean canMove();

}
