package services;

import Exceptions.FibonacciOutOfRangeException;
import com.google.inject.Singleton;
import play.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhengf1 on 4/7/16.
 */
@Singleton
public class FibonacciMapImpl implements Fibonacci{

    private static final Logger.ALogger logger = Logger.of(FibonacciMapImpl.class);

    private static HashMap<BigInteger, BigInteger> cache = new HashMap<BigInteger, BigInteger>();
    private static BigInteger TWO  = new BigInteger("2");
    private static BigInteger ONE  = BigInteger.ONE;
    private static BigInteger ZERO = BigInteger.ZERO;

    // set a max number the Fibonacci instance to hold, to avoid user abuse of it,
    // and cause out of memory issue.
    private BigInteger maxIndex;

    public FibonacciMapImpl() {
        this(BigInteger.valueOf(100000));
    }

    public FibonacciMapImpl(BigInteger maxIndex) {
        this(maxIndex, false);
    }

    /**
     * create FibonacciMapImpl instance.
     *
     * setting lazyInit to false to boost first time access performance, in the cost of
     * taking a little longer to load the instance.
     *
     * @param maxIndex  -- specify the max boundary
     * @param lazyInit  -- specify if pre-compute Fibonacci sequence and fill the cache
     */
    public FibonacciMapImpl(BigInteger maxIndex, boolean lazyInit) {
        logger.info("initialize FibonacciMapImpl: maxIndex=" + maxIndex + ", lazyInit=" + lazyInit);
        if (maxIndex.compareTo(ZERO) > 0) {
            this.maxIndex = maxIndex;
        } else {
            logger.warn("can't set max to a negative value, reset it to 100,000");
            this.maxIndex = new BigInteger("100000");
        }

        // populate cache to boost first time access performance.
        // to avoid StackOverflowError
        if (!lazyInit) {
            BigInteger step = new BigInteger("1024");
            for (BigInteger i = step; i.compareTo(maxIndex) < 0; i = i.add(step)) {
                try {
                    logger.debug("initialization, pre-compute: " + i);
                    get(i);
                } catch (FibonacciOutOfRangeException ex) {
                    logger.error("error in initialization");
                }
            }
        }
    }

    @Override
    public BigInteger get(BigInteger n) throws FibonacciOutOfRangeException {
        validateInput(n);

        // check cache
        if (n.equals(ZERO)) return ZERO;
        if (n.equals(ONE))  return ONE;
        if (cache.containsKey(n)) return cache.get(n);

        // compute and cache
        BigInteger result = get(n.subtract(TWO)).add(get(n.subtract(ONE)));
        cache.put(n, result);
        return result;
    }

    @Override
    public List<BigInteger> getSequenceTo(BigInteger n) throws FibonacciOutOfRangeException {
        validateInput(n);

        List<BigInteger> result = new ArrayList<BigInteger>();
        for (BigInteger i = ZERO; i.compareTo(n) < 0; i = i.add(ONE)) {
            result.add(get(i));
        }
        return result;
    }

    @Override
    public List<FibonacciPair> getRange(BigInteger start, BigInteger size) throws FibonacciOutOfRangeException {
        BigInteger end = start.add(size);

        // switch start with end, if start > end
        if (start.compareTo(end) > 0) {
            BigInteger temp = start;
            start = end;
            end = temp;
        }
        validateInput(end);

        List<FibonacciPair> result = new ArrayList<FibonacciPair>();
        for (BigInteger i = start; i.compareTo(end) < 0; i=i.add(ONE)) {
            result.add(new FibonacciPair(i,get(i)));
        }

        return result;
    }

    private void validateInput(BigInteger i) throws FibonacciOutOfRangeException{
        // boundary check
        if (i.compareTo(ZERO) < 0 || i.compareTo(maxIndex) >0) {
            throw new FibonacciOutOfRangeException("input '" + i + "' is out of range: " + 0
                    + " - " + maxIndex.toString() );
        }
    }

    @Override
    public BigInteger getIndexMax() {
        return maxIndex;
    }
}