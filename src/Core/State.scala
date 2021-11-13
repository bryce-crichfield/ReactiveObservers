package Core

import Component.Entity
import Component.UI.Button
import Component.UI.Container.UIFreeContainer

import java.awt.Color

trait State {
//  val tileMap: TileMap = new TileMap()
  val entities: Set[Entity] = Set()

  // -------------------------------------------------------------------------
  val UI: UIFreeContainer = new UIFreeContainer()
  val b1 = new Button {
    position (100, 100)
    size (50, 50)
    background (Color.RED)
    onClick { background match {
      case Color.RED => this background Color.BLACK
      case Color.BLACK => this background Color.RED
      }
    }
  }

  UI add b1

}