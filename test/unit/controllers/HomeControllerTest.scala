package unit.controllers

import org.scalatest.GivenWhenThen
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._


class HomeControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting with GivenWhenThen {

  "HomeController#index" should {
    "be valid" in {
      Given("fake get on /")
      val fakeRequest = FakeRequest(GET, "/")

      When("request is sent to app")
      val renderedPage = route(app, fakeRequest).get

      Then("response should be valid")
      contentType(renderedPage) mustBe Some("text/html")
      status(renderedPage) mustBe OK
    }
  }
}
