package annotations;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class CORSFilter extends Action.Simple {

	@Override
	public Result call(Context arg0) throws Throwable {
		if (arg0.request().getHeader("origin") != null) {
			arg0.response().setHeader("Access-Control-Allow-Origin", //
					"*");
			arg0.response().setHeader("Access-Control-Allow-Methods", //
					"POST, GET, OPTIONS, PUT, DELETE");
			arg0.response().setHeader("Access-Control-Allow-Headers", //
					"Content-Type, X-Requested-With, Key, Accept, Origin");
		}

		String key = arg0.request().getHeader("Key");
		System.out.println("Key : " + key);
		if ("OPTIONS".equals(arg0.request().method())
				|| "tagartoys".equals(key) //
				|| ("testkey".equals(key) //
				&& arg0.request().method().equals("GET"))) {
			return delegate.call(arg0);
		}

		return unauthorized("Not authorized");

	}
}
