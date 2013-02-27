package controllers;

import java.util.List;

import models.SpeechTherapyActivity;
import models.SpeechTherapyActivity.Type;
import models.activities.TextToImagesActivityPart;
import models.activities.TextToImagesItem;
import play.db.ebean.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	@Transactional 
	public static Result activities() {
		List<SpeechTherapyActivity> all = SpeechTherapyActivity.find.all();
		for (SpeechTherapyActivity speechTherapyActivity : all) {
			speechTherapyActivity.delete();
		}

		addActivity();

		return ok(Json.toJson(SpeechTherapyActivity.find.all()));
	}

	private static void addActivity() {
		SpeechTherapyActivity activity = new SpeechTherapyActivity();
		activity.name = "Repérage des syllabes cibles";
		activity.description = "Retrouver le mot qui contient la syllabe présentée";
		activity.pictureUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		activity.type = Type.TEXT_TO_IMAGES;

		activity.save();
		
		TextToImagesActivityPart textToImagesActivityPart = new TextToImagesActivityPart();
		textToImagesActivityPart.text = "mo";
		TextToImagesItem item;

		item = new TextToImagesItem();
		item.name = "locomotive";
		item.pictureUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		item = new TextToImagesItem();
		item.name = "caméra";
		item.pictureUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		item = new TextToImagesItem();
		item.name = "domino";
		item.pictureUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		item = new TextToImagesItem();
		item.name = "tableau";
		item.pictureUrl = "http://officeimg.vo.msecnd.net/en-us/images/MH900237619.jpg";
		textToImagesActivityPart.activityItems.add(item);

		activity.parts.add(textToImagesActivityPart);
		activity.save();
	}

	public static Result index() {
		return ok("");
	}

}
