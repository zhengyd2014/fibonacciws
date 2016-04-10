package utils;

/**
 * Created by zhengf1 on 4/10/16.
 */
public class StopWatch {

    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }

    /**
     * milliseconds elapsed since start
     *
     * @return
     */
    public long elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start);
    }
}
