package domain.move;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Objects;

import static com.google.common.collect.Streams.zip;
import static java.util.Arrays.stream;

@EqualsAndHashCode
public class Vector {

    @NonNull
    private final Integer[] coordinates;

    public Vector(@NonNull Integer[] coordinates) {
        stream(coordinates).forEach(Objects::requireNonNull);
        this.coordinates = coordinates;
    }

    public static Vector of(@NonNull Integer[] coordinates) {
        return new Vector(coordinates);
    }

    public Vector plus(@NonNull Vector addition) {
        return Vector.of(
                zip(stream(this.coordinates), stream(addition.coordinates), Integer::sum).toArray(Integer[]::new)
        );
    }

}