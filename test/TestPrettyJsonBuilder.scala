import org.scalatest.WordSpec
import formats.NodeFormats._
import TestPrettyJsonBuilder._
import models.Node
import play.api.libs.json.{Json, Reads}

class TestPrettyJsonBuilder extends WordSpec{

}

private object TestPrettyJsonBuilder {
  val jsonString: String =
    """
      |[ {
      |  "id" : 1,
      |  "name" : "A",
      |  "nodes" : [ {
      |    "id" : 2,
      |    "name" : "AA",
      |    "nodes" : [ {
      |      "id" : 3,
      |      "name" : "AA1",
      |      "nodes" : [ ]
      |    }, {
      |      "id" : 4,
      |      "name" : "AA2",
      |      "nodes" : [ ]
      |    } ]
      |  }, {
      |    "id" : 5,
      |    "name" : "AB",
      |    "nodes" : [ ]
      |  } ]
      |}, {
      |  "id" : 6,
      |  "name" : "B",
      |  "nodes" : [ ]
      |}, {
      |  "id" : 7,
      |  "name" : "C",
      |  "nodes" : [ {
      |    "id" : 8,
      |    "name" : "CA",
      |    "nodes" : [ {
      |      "id" : 9,
      |      "name" : "CA1",
      |      "nodes" : [ ]
      |    }, {
      |      "id" : 10,
      |      "name" : "CA2",
      |      "nodes" : [ ]
      |    } ]
      |  } ]
      |}, {
      |  "id" : 11,
      |  "name" : "D",
      |  "nodes" : [ {
      |    "id" : 12,
      |    "name" : "DA",
      |    "nodes" : [ ]
      |  } ]
      |} ]
      |""".stripMargin
}
