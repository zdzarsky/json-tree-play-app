package controllers

import domain.PrettyJsonBuilder
import formats.NodeFormats._
import forms.TextForm
import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import play.twirl.api.Html

import scala.util.{Failure, Success, Try}
import scala.xml.Elem

class FormController @Inject()(components: ControllerComponents) extends AbstractController(components) {
  def handleFormData(): Action[AnyContent] = Action { implicit request =>
    Try(validate(TextForm.form.bindFromRequest.get.text)) match {
      case Failure(exception) => BadRequest(Html(badRequestMessage(exception.getMessage).toString))
      case Success(nodesList) => nodesList.asOpt match {
        case Some(nodesList) => Ok(views.html.results(Html(
          <ul class="parent">
            {PrettyJsonBuilder.build(nodesList)}
          </ul>.toString()
        )))
        case None => BadRequest(Html(badRequestMessage("The json is not nodes list json").toString))
      }
    }
  }

  private def badRequestMessage(message: String): Elem = {
    <div>
      <h1>Invalid Json</h1>
      <h2>Reason:
        {message}
      </h2>
    </div>
  }

}
