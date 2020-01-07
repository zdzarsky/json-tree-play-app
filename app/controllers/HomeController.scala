package controllers

import forms.TextForm
import javax.inject.Inject
import play.api.i18n.I18nSupport
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

class HomeController @Inject()(components: ControllerComponents) extends AbstractController(components) with I18nSupport {
  def index(): Action[AnyContent] = Action { implicit request =>
    Ok(views.html.index(TextForm.form))
  }
}
