package formats

import models.Node
import play.api.libs.json.{JsResult, Json, OFormat}

object NodeFormats {
  implicit val nodeFormats: OFormat[Node] = Json.format[Node]

  def validate(text: String): JsResult[List[Node]] = Json.parse(text).validate[List[Node]]
}