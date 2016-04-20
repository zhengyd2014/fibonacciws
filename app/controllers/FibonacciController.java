package controllers;

import Exceptions.FibonacciOutOfRangeException;

import play.Logger;
import services.FibonacciPair;
import play.libs.Json;
import play.i18n.Messages;
import play.mvc.*;

import services.Fibonacci;
import utils.Helper;
import views.html.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class FibonacciController extends Controller {

    private static final Logger.ALogger logger = Logger.of(FibonacciController.class);

    private Fibonacci fib;

    @Inject
    public FibonacciController(Fibonacci fib) {
        this.fib = fib;
    }

    public Result html() {
        logger.info("FibonacciController: " + this.toString());
        return ok(index.render());
    }

    public Result number(String index) {

        BigInteger result = null;
        try {
            result = fib.get(new BigInteger(index));
        } catch (FibonacciOutOfRangeException ex) {
            return badRequest(Messages.get("exception.outofrange", index));
        } catch (NumberFormatException nfex) {
            return badRequest(Messages.get("exception.invalidnumber", index));
        }

        return ok(Json.toJson(result));
    }

    public Result list(String index) {
        List<BigInteger> result = new ArrayList<>();
        try {
            result = fib.getSequenceTo(new BigInteger(index));
        } catch (FibonacciOutOfRangeException ex) {
            return badRequest(Messages.get("exception.outofrange", index));
        } catch (NumberFormatException nfex) {
            return badRequest(Messages.get("exception.invalidnumber", index));
        }

        return ok(Json.toJson(result));
    }

    public Result range() {
        String startIndex = Helper.getParameterFromRequest(request(), "start", "0");
        String endIndex = Helper.getParameterFromRequest(request(), "size", "100");

        List<FibonacciPair> result = new ArrayList<FibonacciPair>();
        try {
            result = fib.getRange(new BigInteger(startIndex), new BigInteger(endIndex));
        } catch (FibonacciOutOfRangeException ex) {
            return badRequest(Messages.get("exception.outofrange", endIndex));
        } catch (NumberFormatException nfex) {
            return badRequest(Messages.get("exception.invalidnumber", startIndex));
        }

        return ok(Json.toJson(result));
    }
}
