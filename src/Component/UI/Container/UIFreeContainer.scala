package Component.UI.Container

import Component.UI.UIComponent

class UIFreeContainer() extends UIContainer {
  override def update(): Unit =
    children foreach(c => c.update())
}
