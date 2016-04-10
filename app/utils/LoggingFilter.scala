package utils

import play.api.Logger
import play.api.mvc._
import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext


/**
 * Created by zhengf1 on 4/9/16.
 *
 * logging time spent of every request, could be used for:
 *  1. identify time-consuming requests
 *  2. statistics for APIs called
 *  3. audit for errors requests and where they are from
 */
class LoggingFilter extends Filter {

  def apply(nextFilter: RequestHeader => Future[Result])
           (requestHeader: RequestHeader): Future[Result] = {

        val startTime = System.currentTimeMillis
        nextFilter(requestHeader).map { result =>

        val endTime = System.currentTimeMillis
        val requestTime = endTime - startTime

        val format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS")
        val start = format.format(startTime)


        Logger.info(s"${requestHeader.method} ${requestHeader.uri} from ${requestHeader.remoteAddress} start at ${start} " +
          s"took ${requestTime}ms and returned ${result.header.status}")

        result.withHeaders("Request-Time" -> requestTime.toString)
    }
  }
}
