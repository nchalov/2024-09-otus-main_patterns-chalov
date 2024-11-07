package common;

public interface Command {

    void handle();

    default boolean canHandle() {
        return true;
    }

}
