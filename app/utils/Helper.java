package utils;

import Exceptions.FibonacciInvalidNumberException;
import org.apache.commons.lang3.StringUtils;
import play.Logger;
import play.i18n.Messages;
import play.mvc.Http;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengf1 on 4/8/16.
 */
public class Helper {

    private static final Logger.ALogger logger = Logger.of(Helper.class);

    /**
     * convert list to a array like string, with format as
     * "[item_1,item_2, ...,item_n]"
     *
     * @param list
     * @return
     */
    public static String listToString(List list) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[");
        for (int i=0; i<list.size(); i++) {
            buffer.append(list.get(i).toString());
            if (i != list.size()-1) {
                buffer.append(",");
            }
        }
        buffer.append("]");
        return buffer.toString();
    }


    /**
     * print all keys and values in http request
     *
     * @param request
     */
    public static void printRequestQueryParameters(Http.Request request) {
        Map<String, String[]> parameters = request.queryString();
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            logger.info(key + " = " + values[0]);
        }
    }

    /**
     * get specified parameter value from http request, if vale is null, return default value
     *
     * @param request
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getParameterFromRequest(Http.Request request, String name, String defaultValue) {
        Map<String, String[]> parameters = request.queryString();
        if (parameters.get(name) != null && !StringUtils.isEmpty(parameters.get(name)[0])) {
            return parameters.get(name)[0];
        } else {
            return defaultValue;
        }
    }

}
