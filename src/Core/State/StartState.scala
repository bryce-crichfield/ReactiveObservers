package Core.State

import Component.Size
import Component.UI.Container.{UIFlexContainer, UIFreeContainer, UIGridContainer, Vertical}
import Component.UI.Control.Button

import java.awt.Color

class StartState extends State {
  // [ FIELDS ]  -----------------------------------------------------------------------

  protected val GridBox = new UIGridContainer(Size(3, 3))
  GridBox position (50, 50)
  GridBox size 400
  GridBox background Color.BLUE
  GridBox access (0, 0) background Color.RED
  GridBox border (4, Color.DARK_GRAY)
  protected val EnterButton = new Button {
    background (Color.GRAY)
    position(500, 300)
    size(150, 60)
    border (4, Color.RED)
  }
//
//  EnterButton onEnter {
//    EnterButton border(Color.DARK_GRAY)
//  }
//
//  EnterButton onExit {
//    EnterButton border(Color.BLACK)
//  }
//
  EnterButton onClick {
    val grid = GridBox.access(0,0)
    val color: Color = grid.background
      color match {
      case Color.BLUE => grid.background(Color.RED)
      case Color.RED => grid.background(Color.BLUE)
    }
  }
//  GridBox.add(EnterButton, 0, 0)
  val free = new UIFreeContainer
  free.add(GridBox)
  free.add(EnterButton)
  _UI = free

  // [ GETTERS ] -----------------------------------------------------------------------

  // [ SETTERS ] -----------------------------------------------------------------------

  // [ METHODS ] -----------------------------------------------------------------------

}
