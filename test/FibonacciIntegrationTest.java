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
        logger.info("start testInServer testing");
        logger.info("integration test: test fibonacci service against a running server");
        TestServer server = testServer(3333);
        running(server, () -> {
            try {
                WSClient ws = WS.newClient(3333);

                logger.info("test GET /fibonacci/number/10");
                F.Promise<WSResponse> completionStage = ws.url("/fibonacci/number/10").get();
                WSResponse response = completionStage.get(10, TimeUnit.SECONDS);
                assertEquals(OK, response.getStatus());
                logger.info(response.getBody());
                assertEquals("55", response.getBody());

                logger.info("test GET /fibonacci/list/10");
                completionStage = ws.url("/fibonacci/list/10").get();
                response = completionStage.get(10, TimeUnit.SECONDS);
                assertEquals(OK, response.getStatus());
                logger.info(response.getBody());
                assertEquals("[0,1,1,2,3,5,8,13,21,34]", response.getBody());

                ws.close();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                fail(e.getMessage());
            }
        });
        logger.info("passed testInServer test");
    }
}
