package unit.controllers

import models.Node
import org.scalatest.GivenWhenThen
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers.{POST, contentAsString, contentType, route, status, _}
import play.api.test.{FakeRequest, Injecting}
import formats.NodeFormats._
import play.api.libs.json.{JsValue, Json}
import FormControllerTest._

class FormControllerTest extends PlaySpec with GuiceOneAppPerTest with Injecting with GivenWhenThen {

  "FormController#handleFormData" should {
    "be valid on simple node" in {
      Given("valid post with json body")
      val request = FakeRequest(POST, endpoint).withFormUrlEncodedBody("text" -> validJson.toString())
      When("sent")
      val result = route(app, request).get
      Then("response should contain name and id")
      status(result) mustBe OK
      contentType(result) mustBe Some("text/html")
      contentAsString(result) must include("100")
      contentAsString(result) must include("AAA")
    }

    "handle rubbish" in {
      Given("request with rubbish")
      val wrongRequest = FakeRequest(POST, endpoint).withFormUrlEncodedBody("text" -> "rubbish")
      When("sent")
      val result = route(app, wrongRequest).get
      Then("response should contain failure info and reason")
      status(result) mustBe BAD_REQUEST
      contentAsString(result) must include("Invalid Json")
      contentAsString(result) must include("Reason")
    }

    "handle invalid json type" in {
      Given("request with some improper json")
      val wrongRequest = FakeRequest(POST, endpoint)
        .withFormUrlEncodedBody("text" -> invalidJson.toString())

      When("sent")
      val result = route(app, wrongRequest).get

      Then("response should contain failure info and reason")
      status(result) mustBe BAD_REQUEST
      contentAsString(result) must include("Invalid Json")
      contentAsString(result) must include("Reason")
    }
  }
}

object FormControllerTest {
  val endpoint = "/results"
  val validJson : JsValue = Json.toJson(List(Node(id = 100, name = "AAA", nodes = List.empty)))
  val invalidJson: JsValue = Json.toJson(
    "foo" -> "bar",
    "nodes" -> Json.arr()
  )
}
