package Core.State

import Component.UI.Container.{UIFlexContainer, Vertical}
import Component.UI.Control.Button

import java.awt.Color

class StartState extends State {
  // [ FIELDS ]  -----------------------------------------------------------------------

  protected val MenuBox = new UIFlexContainer(Vertical) {
    background(Color.WHITE)
  }

  protected val EnterButton = new Button {
    background (Color.RED)
    size(150, 60)
    border (10, Color.RED)
    text("Enter Program")
  }

  EnterButton onEnter {
    EnterButton border(Color.DARK_GRAY)
  }

  EnterButton onExit {
    EnterButton border(Color.BLACK)
  }

  EnterButton onClick {
    StateManager.transition(new MainState())
  }
  _UI = MenuBox
  MenuBox.add(EnterButton)

  // [ GETTERS ] -----------------------------------------------------------------------

  // [ SETTERS ] -----------------------------------------------------------------------

  // [ METHODS ] -----------------------------------------------------------------------

}
