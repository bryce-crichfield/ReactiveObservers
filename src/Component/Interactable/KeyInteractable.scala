package Component.Interactable

import Component.Component
import Input.KeyFlag

trait KeyInteractable[C <: Component] {
  this: C =>
  private val interactor = new KeyInteractor[C](this)

  def onPress(f: PartialFunction[KeyFlag, C => Unit]): Unit = {
    interactor._onPress = Some(f orElse interactor.default)
  }
}
