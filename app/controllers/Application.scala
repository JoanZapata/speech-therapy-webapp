package controllers

import play.api._
import play.api.mvc._
import reactivemongo.api._
import reactivemongo.bson._
import reactivemongo.bson.handlers.DefaultBSONHandlers._
import play.modules.reactivemongo._
import play.modules.reactivemongo.PlayBsonImplicits._
import play.api.libs.json._
import play.api.Play.current
import scala.collection.immutable.Nil
import java.util.Date
import play.api.libs.json.JsString
import java.text.DateFormat

object All extends QueryBuilder

object Application extends Controller with MongoController {
  val db = ReactiveMongoPlugin.db
  lazy val collection = db("speech-therapy")

  val dateFormat = DateFormat.getDateInstance(DateFormat.SHORT);
  implicit def Date2String[Date](d: Date): String = dateFormat.format(d)

  // Find all the activities
  def findAll() = Action {
    Async {
      collection.find[JsValue](All).toList.map { activities =>
        Ok(JsArray(activities.map(_ match {
          case o: JsObject => filterId(o)
          case o => throw new IllegalArgumentException()
        })))
      }
    }
  }

  // Find one activity
  def findById(id: String) = Action {
    Async {
      val qualifier = Json.obj("_id" -> Json.obj("$oid" -> id))
      collection.find[JsValue, JsValue](qualifier).toList.map { activities =>
        activities(0) match {
          case o: JsObject => Ok(filterId(o))
          case _ => throw new IllegalArgumentException()
        }
      }
    }
  }

  // Add an activity
  def addActivity() = Action { request =>
    Async {
      // Add a created date
      val save = request.body.asJson.get match {
        case o: JsObject => JsObject(("created" -> JsString(new Date())) :: ("modified" -> JsString(new Date())) :: o.value.toList)
        case o => throw new IllegalArgumentException()
      }
      collection.insert[JsValue](save).map(_ =>
        Ok(""))
    }
  }

  // Update an activity
  def updateActivity(id: String) = Action { request =>
    Async {
      val qualifier = Json.obj("_id" -> Json.obj("$oid" -> id))
      val activity = request.body.asJson.get match {
        case o: JsObject => JsObject(o.value.filterKeys(_ != "id").toList)
        case o => throw new IllegalArgumentException()
      }
      collection.update[JsValue, JsValue](
        qualifier, activity).map(
          _ => Ok(""))
    }
  }

  // Delete an activity
  def deleteActivity(id: String) = Action { request =>
    Async {
      val qualifier = Json.obj("_id" -> Json.obj("$oid" -> id))
      collection.remove[JsValue](qualifier).map(_ =>
        Ok(""))
    }
  }

  def filterId(o: JsObject): JsObject = {
    val seq = o.value.toList.map {
      case (key, value) => key match {
        case "_id" => "id" -> value \ "$oid"
        case _ => (key, value)
      }
    }
    JsObject(seq)
  }
}

