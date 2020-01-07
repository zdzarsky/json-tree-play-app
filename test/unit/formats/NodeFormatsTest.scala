package unit.formats

import formats.NodeFormats
import org.scalatest.WordSpec
import unit._

import scala.util.Try

class NodeFormatsTest extends WordSpec{
  "NodeFormats validate" should {
    "validate correct JSON" in {
      assert(NodeFormats.validate(testJsonValue).isSuccess)
    }

    "read JSError for empty JSON" in {
      assert(NodeFormats.validate("{}").isError)
    }

    "throw JsonParseException for incorrectly structured JSON" in {
      assert(Try(NodeFormats.validate("{something}")).isFailure)
    }
  }
}
