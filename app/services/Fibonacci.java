package services;

import Exceptions.FibonacciOutOfRangeException;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by zhengf1 on 4/7/16.
 */
public interface Fibonacci {

    public BigInteger get(BigInteger i) throws FibonacciOutOfRangeException;

    public List<BigInteger> getSequenceTo(BigInteger i) throws FibonacciOutOfRangeException;

    public List<FibonacciPair> getRange(BigInteger start, BigInteger size) throws FibonacciOutOfRangeException;

    public BigInteger getIndexMax();
}
