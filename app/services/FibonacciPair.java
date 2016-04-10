package services;

import java.math.BigInteger;

/**
 * used for returning range to client, like
 * [{"3", "2"}, {"4", "3"}, {"5","5"}, {"6","8"}, {"7", "13"}]
 *
 * Created by zhengf1 on 4/9/16.
 */
public class FibonacciPair {
    private BigInteger index;
    private BigInteger value;

    public FibonacciPair(BigInteger index, BigInteger value) {
        this.index = index;
        this.value = value;
    }


    public BigInteger getIndex() {
        return index;
    }

    public void setIndex(BigInteger index) {
        this.index = index;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }
}
