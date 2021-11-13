package Component.UI.Container

class UIFreeContainer() extends UIContainer {
  override def update(): Unit =
    children foreach(c => c.update())
}
