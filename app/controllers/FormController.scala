package controllers

import domain.PrettyJsonBuilder
import forms.TextForm
import javax.inject.Inject
import models.Node
import play.api.libs.json._
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import formats.NodeFormats._
import play.twirl.api.Html

import scala.util.{Failure, Success, Try}
import scala.xml.Elem

class FormController @Inject()(components: ControllerComponents) extends AbstractController(components) {
  def handleFormData(): Action[AnyContent] = Action { implicit request =>
    Try(
      Json.parse(
        TextForm.form.bindFromRequest.get.text
      ).validate[List[Node]]) match {
      case Failure(exception) =>
        BadRequest(Html(badRequestMessage(exception).toString))
      case Success(nodesList) =>
        Ok(views.html.results(Html(
          <ul class="parent">
            {PrettyJsonBuilder.build(nodesList.get)}
          </ul>.toString()
        )))

    }
  }

  private def badRequestMessage(exception: Throwable): Elem = {
    <div>
      <h1>Invalid Json</h1>
      <h2>Reason:
        {exception.getMessage}
      </h2>
    </div>
  }

}
