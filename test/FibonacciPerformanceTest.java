import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.Logger;
import services.Fibonacci;
import services.FibonacciMapImpl;
import utils.StopWatch;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by zhengf1 on 4/8/16.
 */
public class FibonacciPerformanceTest {

    private static final Logger.ALogger logger = Logger.of(FibonacciPerformanceTest.class);

    private static Fibonacci fib;

    @BeforeClass
    public static void setup() {
        fib = new FibonacciMapImpl(new BigInteger("10000"));
    }

    @AfterClass
    public static void teardown() {
        fib = null;
    }

    @Test
    public void bigIndexPerformance() throws Exception {
        logger.info("start bigIndexPerformance testing");
        logger.info("performance test: verify the time of getting a big index fibonacci number less than 10 ms");
        StopWatch stopWatch = new StopWatch();
        BigInteger index = fib.getIndexMax().subtract(BigInteger.ONE);
        fib.get(index);
        double timeSpent = stopWatch.elapsedTime();
        logger.info("time spent to get " + index + "-th fibonacci number: " + timeSpent + " milliseconds");
        logger.info("passed bigIndexPerformance test.");
        assertTrue(timeSpent < 10);
    }
}
