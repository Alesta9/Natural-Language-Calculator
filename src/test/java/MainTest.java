import org.example.Main;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    public void testCalculateDecimalNum() {


        assertEquals(2.0, Main.calculateDecimalNum(4.0, 2.0),0.0001);
        assertEquals(3.0, Main.calculateDecimalNum(6.0, 2.0),0.0001);
        assertEquals(0.5, Main.calculateDecimalNum(1.0, 2.0),0.0001);

    }

    @Test
    public void testExtractMultiplyOrDivide() {

        assertEquals(8.0, Main.extractMultiplyOrDivide(new ArrayList<>(Arrays.asList(2.0, '*', 4.0))).get(0));
        assertEquals(2.0, Main.extractMultiplyOrDivide(new ArrayList<>(Arrays.asList(4.0, '/', 2.0))).get(0));
        assertEquals(6.0, Main.extractMultiplyOrDivide(new ArrayList<>(Arrays.asList(2.0, '*', 3.0, '/', 1.0))).get(0));
        assertEquals(4.0, Main.extractMultiplyOrDivide(new ArrayList<>(Collections.singletonList(4.0))).get(0));

    }

    @Test
    public void testCalculateResult() {

        assertEquals(9.0, Main.calculateResult(new ArrayList<>(Arrays.asList(5.0, '+', 4.0))),0.0001);

        assertEquals(10.0, Main.calculateResult(new ArrayList<>(Arrays.asList(5.0, '+', 4.0, '-', 3.0, '+', 4.0))),0.0001);

    }

    @Test
    public void testFixArray() {

        ArrayList<Object> testArray = new ArrayList<>(Arrays.asList(1.0, '+', 2.0, '-', 3.0));
        assertEquals(Arrays.asList(1.0, '+', 5.0), Main.fixArray(testArray, 2, 5.0));

    }

    @Test
    public void testExpressionsHasMultiplyOrDivide() {

        assertTrue(Main.expressionsHasMultiplyOrDivide(new ArrayList<>(Arrays.asList(2.0, '*', 4.0))));
        assertTrue(Main.expressionsHasMultiplyOrDivide(new ArrayList<>(Arrays.asList(4.0, '/', 2.0))));
        assertTrue(Main.expressionsHasMultiplyOrDivide(new ArrayList<>(Arrays.asList(2.0, '*', 3.0, '/', 1.0))));
        assertFalse(Main.expressionsHasMultiplyOrDivide(new ArrayList<>(Collections.singletonList(4.0))));

    }



    @Test
    public void testCalculate() {

        assertEquals(5.0, Main.calculate(2.0, 3.0, '+'),0.0001);
        assertEquals(1.0, Main.calculate(3.0, 2.0, '-'),0.0001);
        assertEquals(8.0, Main.calculate(2.0, 4.0, '*'),0.0001);
        assertEquals(2.0, Main.calculate(4.0, 2.0, '/'),0.0001);

    }

    @Test
    public void testGetOperator() {

        assertEquals('+', Main.getOperator("add"));
        assertEquals('-', Main.getOperator("subtract"));
        assertEquals('*', Main.getOperator("multiplied-by"));
        assertEquals('/', Main.getOperator("divided-by"));
        assertEquals('N', Main.getOperator("invalid"));

    }

    @Test
    public void testGetNumber() {

        assertEquals(1.0, Main.getNumber("one"),0.0001);
        assertEquals(2.0, Main.getNumber("two"),0.0001);
        assertEquals(3.0, Main.getNumber("three"),0.0001);
        assertEquals(0.0, Main.getNumber("invalid"),0.0001);

    }
}