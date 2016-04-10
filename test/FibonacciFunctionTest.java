import Exceptions.FibonacciOutOfRangeException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Logger;
import services.Fibonacci;
import services.FibonacciMapImpl;
import utils.Helper;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhengf1 on 4/7/16.
 */
public class FibonacciFunctionTest {

    private static final Logger.ALogger logger = Logger.of(FibonacciFunctionTest.class);

    private static Fibonacci fib;
    private static String[][] expected = {
            {"0", "0"}, {"1", "1"}, {"10", "55"},
            {"19", "4181"}, {"100", "354224848179261915075"}
    };

    @BeforeClass
    public static void setup() {
        fib = new FibonacciMapImpl(new BigInteger("10000"), true);

    }

    @AfterClass
    public static void teardown() {
        fib = null;
    }

    @Test
    public void simpleCheck() throws Exception {
        logger.info("start simpleCheck testing");
        logger.info("positive test: to check FibonacciMapImpl can get specified fibonacci number right.");
        for (int i=0; i<expected.length; i++) {
            BigInteger result = fib.get(new BigInteger(expected[i][0]));
            assertEquals(expected[i][1], result.toString());
        }
        logger.info("simpleCheck test passed");
    }

    @Test
    public void inputValidate() throws Exception {
        logger.info("start inputValidate testing");
        logger.info("negative test: to verify exception when input is a non-number");
        String input = "dfa";
        try {
            new BigInteger(input);
            fail("set index to string, should not reach here.");
        } catch (NumberFormatException ex) {
            logger.info(ex.getMessage());
            assertTrue(ex.getMessage().contains(input));
        }

        logger.info("inputValidate test passed");
    }

    @Test
    public void boundaryTest() {
        logger.info("start boundaryTest testing");
        logger.info("negative tet: to verify exception when input out of support range");
        try {
            fib.get(BigInteger.valueOf(-1));
            fail("set index to -1, should not reach here.");
        } catch (FibonacciOutOfRangeException ex) {
            logger.info(ex.getMessage());
            assertTrue(ex.getMessage().contains("-1"));
        }

        try {
            fib.get(BigInteger.valueOf(10000000));
            fail("set index larger than indexMax, should not reach here.");
        } catch (FibonacciOutOfRangeException ex) {
            logger.info(ex.getMessage());
            assertTrue(ex.getMessage().contains("10000000"));
        }

        logger.info("passed boundaryTest");
    }

    @Test
    public void checkListFunction() throws Exception {
        logger.info("start checkListFunction testing");
        logger.info("positive test: to verify FibonacciMapImpl can get a list of Fibonacci numbers right");
        int index = 110;
        List<BigInteger> result = fib.getSequenceTo(BigInteger.valueOf(index));
        assertEquals(result.size(), index);
        for (int i=0; i<expected.length; i++) {
            assertEquals(result.get(Integer.parseInt(expected[i][0])), new BigInteger(expected[i][1]));
        }
        logger.info(Helper.listToString(result));
        logger.info("passed checkListFunction");
    }

}
