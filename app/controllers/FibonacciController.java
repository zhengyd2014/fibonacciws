package controllers;

import Exceptions.FibonacciOutOfRangeException;
import services.FibonacciPair;
import play.libs.Json;
import play.i18n.Messages;
import play.mvc.*;

import services.Fibonacci;
import services.FibonacciMapImpl;
import utils.Helper;
import views.html.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FibonacciController extends Controller {

    private static Fibonacci fib = new FibonacciMapImpl(new BigInteger("100000"));

    public Result html() {
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
