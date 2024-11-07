package domain.rotate;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class Angle {

    private static final Double MAX = Math.toRadians(360d);

    private static final Double MIN = Math.toRadians(0d);

    @NonNull
    private final Double radians;

    public Angle(@NonNull Double radians) {
        if (nonValid(radians)) {
            throw new IllegalArgumentException("Invalid radian value");
        }
        this.radians = radians;
    }

    public static Angle of(@NonNull Double radians) {
        return new Angle(radians);
    }

    public Angle plus(@NonNull Angle addition) {
        Double result = radians + addition.radians;
        if (result.compareTo(MAX) > 0) {
            result -= MAX;
        }
        return Angle.of(result);
    }

    private boolean nonValid(Double radians) {
        return isRadiansLessThanToMin(radians) || isRadiansGreaterThanToMax(radians);
    }

    private boolean isRadiansGreaterThanToMax(Double radians) {
        return radians.compareTo(MAX) > 0;
    }

    private boolean isRadiansLessThanToMin(Double radians) {
        return radians.compareTo(MIN) < 0;
    }

}
