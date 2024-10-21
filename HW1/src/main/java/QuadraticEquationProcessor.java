import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class QuadraticEquationProcessor {

    public List<Double> computeQuadraticEquation(Double a, Double b, Double c, Double eps) {
        if (isInvalidArgumentsHasBeenPassed(a, b, c, eps)) {
            throw new IllegalArgumentException("Invalid arguments has been passed");
        }
        return findAllRootsQuadraticEquation(a, b, eps, computeDiscriminantValue(a, b, c));
    }

    private List<Double> findAllRootsQuadraticEquation(Double a, Double b, Double eps, Double discriminant) {
        if (discriminant < -eps) {
            return Collections.emptyList();
        } else if (Math.abs(discriminant) > eps) {
            return findQuadraticEquationRootsForDiscriminantGreaterThanZero(a, b, discriminant);
        } else if (Math.abs(discriminant) <= eps) {
            return findQuadraticEquationRootForDiscriminantEqualsZero(a, b);
        } else {
            throw new RuntimeException("Unknown error has been occurred");
        }
    }

    private boolean isInvalidArgumentsHasBeenPassed(Double a, Double b, Double c, Double eps) {
        return isAnyArgumentMatchesNull(a, b, c, eps)
                || isAnyArgumentNoMatchesDigitValue(a, b, c, eps)
                || isFirstArgumentLessThanOrEqualsMachineEpsilon(a, eps);
    }

    private Boolean isAnyArgumentNoMatchesDigitValue(Double a, Double b, Double c, Double eps) {
        return Stream.of(a, b, c, eps)
                .anyMatch(arg -> Double.isInfinite(arg) || Double.isNaN(arg));
    }

    private Boolean isAnyArgumentMatchesNull(Double a, Double b, Double c, Double eps) {
        return Stream.of(a, b, c, eps)
                .anyMatch(Objects::isNull);
    }

    private Boolean isFirstArgumentLessThanOrEqualsMachineEpsilon(Double a, Double eps) {
        return Math.abs(a) <= eps;
    }

    private Double computeDiscriminantValue(Double a, Double b, Double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }

    private List<Double> findQuadraticEquationRootsForDiscriminantGreaterThanZero(Double a,
                                                                                  Double b,
                                                                                  Double discriminant) {
        var firstEquationsRoot = (-b + Math.sqrt(discriminant)) / (2 * a);
        var secondEquationsRoot = (-b - Math.sqrt(discriminant)) / (2 * a);
        return List.of(firstEquationsRoot, secondEquationsRoot);
    }

    private List<Double> findQuadraticEquationRootForDiscriminantEqualsZero(Double a, Double b) {
        var root = -b / (2 * a);
        return Collections.singletonList(root);
    }

}
