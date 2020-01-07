package domain

import models.Node

import scala.xml.{Elem, NodeBuffer, NodeSeq, Utility}

object PrettyJsonBuilder {
  def build(nodes: List[Node], level: Int = 0): Elem = nodes match {
    case Nil => <ul></ul>
    case head :: tail =>
      <li>
        <span> {if (level > 0) "Nodes"}</span> <span class="caret"></span>
        <ul class="nested">
          <li>Id:
            {head.id}
          </li>
          <li>Name:
            {head.name}
          </li>{build(head.nodes, level + 1)}
        </ul>{build(tail, level)}
      </li>
  }
}
