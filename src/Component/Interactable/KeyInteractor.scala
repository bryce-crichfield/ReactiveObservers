package Component.Interactable

import Component.Component
import Input.{KeyFlag, Keyboard}
import Observation.KeyNotice

sealed class KeyInteractor[C <: Component](component: C) extends InputInteractor[KeyNotice, C] {
  Keyboard subscribe this
  val default: PartialFunction[KeyFlag, C => Unit] = {
    case _ => _ => ()
  }
  var _onPress: Option[PartialFunction[KeyFlag, C => Unit]] = None

  override def respond(notice: KeyNotice): Unit = {
    _onPress match {
      case Some(f) => notice.pressed
        .zipWithIndex
        .filter(t => t._1)
        .foreach(t => f(KeyFlag(t._2))(component))
      case None => ()
    }
  }
}
