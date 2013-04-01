import play.api.mvc._

object Global extends WithFilters(KeyFilter)

object KeyFilter extends Filter {
  override def apply(next: RequestHeader => Result)(request: RequestHeader): Result = {
//    println("hasKey:" + request.method)
    
//      println(request.queryString("key").mkString)
//      println(request.queryString("key").mkString equals "tagartoy")
    if (request.method != "GET" &&
      (!request.queryString.contains("key") ||
        !request.queryString("key").mkString.equals("tagartoy"))) {
      Results.Forbidden
    } else {
      next(request)
    }
  }
}