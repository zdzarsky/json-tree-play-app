package formats

import models.Node
import play.api.libs.json.{Json, OFormat}

object NodeFormats {
  implicit val nodeFormats: OFormat[Node] = Json.format[Node]
}
