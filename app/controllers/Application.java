package controllers;

import models.ActivityPart;
import models.ActivityResource;
import models.Category;
import models.SpeechTherapyActivity;
import models.SpeechTherapyActivity.Type;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import annotations.AllowCrossDomainRequest;

public class Application extends Controller {

	/**
	 * Cross Origin Request Sharing calls are going to have a pre-flight option
	 * call because we use the "non simple headers" This method catches those,
	 * (headers handling is done in the CORS() method)
	 */

	@AllowCrossDomainRequest
	public static Result options(String url) {
		return ok("");
	}

	@Transactional
	@AllowCrossDomainRequest
	public static Result activities() {
		return ok(Json.toJson(SpeechTherapyActivity.find.all()));
	}

	@Transactional
	@AllowCrossDomainRequest
	public static Result activity(Long id) {
		return ok(Json.toJson(SpeechTherapyActivity.find.byId(id)));
	}

	@Transactional
	@AllowCrossDomainRequest
	public static Result addActivity() {
		SpeechTherapyActivity activity = Json.fromJson(request().body().asJson(),
				SpeechTherapyActivity.class);

		// SpeechTherapyActivity activity =
		// Json.fromJson(request().body().asJson(),
		// SpeechTherapyActivity.class);
		activity.update();
		return ok("OK");
	}

	@Transactional
	public static Result addTestActivity() {
		SpeechTherapyActivity activity = new SpeechTherapyActivity();
		activity.name = "Repérage des syllabes cibles";
		activity.description = "Retrouver le mot qui contient la syllabe présentée";
		activity.pictureUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		activity.type = Type.TEXT_TO_IMAGES;

		Category category = new Category();
		category.id = 1L;
		category.name = "Categorie 1";
		category.description = "Une catégorie de test";
		activity.category = category;
		activity.save();

		ActivityPart textToImagesActivityPart = new ActivityPart();
		textToImagesActivityPart.title = "mo";
		ActivityResource item;

		item = new ActivityResource();
		item.name = "locomotive";
		item.resourceUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		item = new ActivityResource();
		item.name = "caméra";
		item.resourceUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		item = new ActivityResource();
		item.name = "domino";
		item.resourceUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		item = new ActivityResource();
		item.name = "tableau";
		item.resourceUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		activity.parts.add(textToImagesActivityPart);
		activity.save();

		return ok();
	}

	public static Result index() {
		return ok("");
	}

}
