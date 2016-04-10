import org.junit.Test;
import play.Logger;

import play.libs.F;

import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.test.TestServer;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static play.test.Helpers.*;

/**
 * Created by zhengf1 on 4/8/16.
 */
public class FibonacciIntegrationTest {

    private static final Logger.ALogger logger = Logger.of(FibonacciIntegrationTest.class);

    @Test
    public void testInServer() throws Exception {
        TestServer server = testServer(3333);
        running(server, () -> {
            try {
                WSClient ws = WS.newClient(3333);
                F.Promise<WSResponse> completionStage = ws.url("/fibonacci/number/10").get();
                WSResponse response = completionStage.get(10, TimeUnit.SECONDS);
                ws.close();
                assertEquals(OK, response.getStatus());
                logger.info(response.getBody());
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        });
    }
}
