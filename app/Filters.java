/**
 * Created by zhengf1 on 4/9/16.
 */
import javax.inject.*;
import play.api.mvc.EssentialFilter;
import play.http.HttpFilters;
import utils.LoggingFilter;


/**
 * This class configures filters that run on every request. This
 * class is queried by Play to get a list of filters.
 */
@Singleton
public class Filters implements HttpFilters {


    private final LoggingFilter loggingFilter;

    @Inject
    public Filters(LoggingFilter loggingFilter) {
        this.loggingFilter = loggingFilter;
    }

    @Override
    public EssentialFilter[] filters() {
        return new EssentialFilter[] { loggingFilter };
    }

}

