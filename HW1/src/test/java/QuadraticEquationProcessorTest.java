import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuadraticEquationProcessorTest {

    private final QuadraticEquationProcessor calculator = new QuadraticEquationProcessor();

    @ParameterizedTest
    @CsvSource("1.0, 0.0, 1.0, 0.001")
    public void testNoRootsFoundIfDiscriminantLessThanZero(Double a, Double b, Double c, Double eps) {
        var quadraticRoots = calculator.computeQuadraticEquation(a, b, c, eps);
        assertTrue(quadraticRoots.isEmpty());
    }

    @ParameterizedTest
    @CsvSource("1.0, 0.0, -1.0, 0.001")
    public void testAllRootsFoundIfDiscriminantGreaterThanZero(Double a, Double b, Double c, Double eps) {
        var quadraticRoots = calculator.computeQuadraticEquation(a, b, c, eps);
        assertEquals(2, quadraticRoots.size());
    }

    @ParameterizedTest
    @CsvSource("1.0, 2.0, 1.0, 0.001")
    public void testAllRootsFoundIfDiscriminantEqualsZero(Double a, Double b, Double c, Double eps) {
        var quadraticRoots = calculator.computeQuadraticEquation(a, b, c, eps);
        assertEquals(1, quadraticRoots.size());
    }

    @Test
    public void testHasBeenThrownIllegalArgumentExceptionIfFirstArgumentEqualsZero() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.computeQuadraticEquation(0.0, 0.0, 0.0, 0.001));
    }

    @Test
    public void testHasBeenThrownNullPointerExceptionIfAnyArgumentsEqualNull() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.computeQuadraticEquation(null, null, null, null));
    }

    @Test
    public void testHasBeenThrownIllegalArgumentExceptionIfAnyArgumentsEqualPositiveInfinite() {
        assertThrows(IllegalArgumentException.class, () -> calculator.computeQuadraticEquation(
                Double.POSITIVE_INFINITY,
                Double.POSITIVE_INFINITY,
                Double.POSITIVE_INFINITY,
                Double.POSITIVE_INFINITY)
        );
    }

    @Test
    public void testHasBeenThrownIllegalArgumentExceptionIfAnyArgumentsEqualNegativeInfinite() {
        assertThrows(IllegalArgumentException.class, () -> calculator.computeQuadraticEquation(
                Double.NEGATIVE_INFINITY,
                Double.NEGATIVE_INFINITY,
                Double.NEGATIVE_INFINITY,
                Double.NEGATIVE_INFINITY)
        );
    }

    @Test
    public void testHasBeenThrownIllegalArgumentExceptionIfAnyArgumentsEqualNaN() {
        assertThrows(IllegalArgumentException.class, () -> calculator.computeQuadraticEquation(
                Double.NaN,
                Double.NaN,
                Double.NaN,
                Double.NaN)
        );
    }

}
