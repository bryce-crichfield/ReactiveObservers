package Core.State

import Component.Entity
import Component.UI.Container.{UIContainer, UIFreeContainer}
import Component.UI.Control.Button

import java.awt.Color

trait State {
  //  val tileMap: TileMap = new TileMap()
  val entities: Set[Entity] = Set()

  // -------------------------------------------------------------------------
  protected var _UI: UIContainer = new UIFreeContainer()
//  val b1 = new Button {
//    position(100, 100)
//    size(50, 50)
//    background(Color.RED)
//    onClick {
//      background match {
//        case Color.RED => this background Color.BLACK
//        case Color.BLACK => this background Color.RED
//      }
//    }
//  }
//
//  UI add b1

  def UI: UIContainer = _UI
}
