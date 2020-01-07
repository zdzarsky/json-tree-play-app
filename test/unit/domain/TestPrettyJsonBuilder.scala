package unit.domain

import domain.PrettyJsonBuilder
import org.scalatest.{GivenWhenThen, WordSpec}
import play.api.libs.json.{JsValue, Json}
import _root_.formats.NodeFormats
import TestPrettyJsonBuilder._


class TestPrettyJsonBuilder extends WordSpec with GivenWhenThen {
  "PrettyJsonBuilder" should {
    "Build pretty json from valid list of nodes" in {
      Given("Parsed json as list of nodes")
      println(validJsonArray)
      val parsedArray = NodeFormats.validate(validJsonArray.toString()).get
      When("Building pretty json")
      val beautifulJsonXml = PrettyJsonBuilder.build(parsedArray)
      Then("Check equality of manually generated object")
      assert(exampleOutput.filterNot(_.isWhitespace) == beautifulJsonXml.toString.filterNot(_.isWhitespace))
    }
  }
}

private object TestPrettyJsonBuilder {
  val validJsonChild: JsValue = Json.obj(
    "id" -> 3,
    "name" -> "B",
    "nodes" -> Json.arr()
  )

  val validJsonObject: JsValue = Json.obj(
    "id" -> 2,
    "name" -> "A",
    "nodes" -> Json.arr(validJsonChild)
  )

  val validJsonArray: JsValue = Json.arr(
    validJsonObject
  )

  val exampleOutput: String =
    """
      |<li>
      |        <span> </span> <span class="caret"></span>
      |        <ul class="nested">
      |          <li>Id:
      |            2
      |          </li>
      |          <li>Name:
      |            A
      |          </li><li>
      |        <span> Nodes</span> <span class="caret"></span>
      |        <ul class="nested">
      |          <li>Id:
      |            3
      |          </li>
      |          <li>Name:
      |            B
      |          </li><ul></ul>
      |        </ul><ul></ul>
      |      </li>
      |        </ul><ul></ul>
      |      </li>
      |""".stripMargin
}

