import com.google.inject.AbstractModule;
import play.Logger;
import services.Fibonacci;
import services.FibonacciMapImpl;

/**
 * Created by zhengf1 on 4/15/16.
 */
public class Module extends AbstractModule {

    private static final Logger.ALogger logger = Logger.of(Module.class);

    @Override
    public void configure() {
        logger.info("in configure");

        bind(Fibonacci.class).to(FibonacciMapImpl.class);
    }

}
