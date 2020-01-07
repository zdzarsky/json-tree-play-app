package forms

import models.Node
import play.api.data._
import play.api.data.Forms._

final case class TextForm(text: String)

object TextForm {
  val form: Form[TextForm] = Form(
    mapping("text" -> text
    )(TextForm.apply)(TextForm.unapply)
  )
}
