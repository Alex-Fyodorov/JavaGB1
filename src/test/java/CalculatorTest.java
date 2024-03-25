import javaone.lec4.Calculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void init() {
        calculator = new Calculator();
    }

    @Test
    @Disabled
    @DisplayName("test of sum")
    void testAddition() {
        Assertions.assertEquals(10, calculator.sum(3, 7), "sum should be equal 10");
    }

    @Test
    //@Disabled
    @DisplayName("test of multiply")
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    void testMultiplication() {
        Assertions.assertEquals(15, calculator.multiply(3, 5));
    }

    @Test
    void testDivision() {
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.division(10, 0));
    }

    @CsvSource({
            "1, 2, 3",
            "3, 5, 8",
            "10, 20, 30"
    })
    @ParameterizedTest
    void testAdditionMultiple(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.sum(a, b), "sum should be equal 10");
    }

    @MethodSource("dataForAddition")
    @ParameterizedTest
    void testAdditionMultipleWithMethod(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.sum(a, b), "sum should be equal 10");
    }

    public static Stream<Arguments> dataForAddition() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int a = random.nextInt(1000000);
            int b = random.nextInt(1000000);
            arguments.add(Arguments.arguments(a, b, a + b));
        }
        return arguments.stream();
    }

    @MethodSource("dataForDivision")
    @Disabled
    @ParameterizedTest
    void testDivisionMultipleWithMethod(int a, int b, int result) {
        Assertions.assertEquals(result, calculator.division(a, b), "sum should be equal 10");
    }

    public static Stream<Arguments> dataForDivision() {
        List<Arguments> arguments = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            int a = random.nextInt(200) - 100;
            int b = random.nextInt(200) - 100;
            arguments.add(Arguments.arguments(a, b, a / b));
        }
        return arguments.stream();
    }
}
