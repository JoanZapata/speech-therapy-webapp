import play.api.mvc._

object Global extends WithFilters(KeyFilter)

import play.api.Play

object KeyFilter extends Filter {

  override def apply(next: RequestHeader => Result)(request: RequestHeader): Result = {
    val password = Play.current.configuration.getString("app.password").get
    if (request.method != "GET" &&
      (!request.queryString.contains("key") ||
        !request.queryString("key").mkString.equals(password)))
      Results.Forbidden
    else next(request)
  }

}