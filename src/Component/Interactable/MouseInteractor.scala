package Component.Interactable

import Component.Component
import Input.{Clicked, Dragged, Mouse, Moved}
import Observation.MouseNotice

sealed class MouseInteractor[C <: Component](component: C) extends InputInteractor[MouseNotice, C] {
  Mouse subscribe this
  private var wasHovering: Boolean = false
  var _onClick: Option[(MouseNotice, C) => Unit] = None
  var _onEnter: Option[(MouseNotice, C) => Unit] = None
  var _onExit: Option[(MouseNotice, C) => Unit] = None
  var _onDrag: Option[(MouseNotice, C) => Unit] = None

  override def respond(notice: MouseNotice): Unit = {
    val nowHovering = notice.p.collides(component.bounds)
    notice.flag match {
      case Clicked if nowHovering =>
        _onClick foreach { f => f(notice, component) }
      case Moved if nowHovering && !wasHovering =>
        _onEnter foreach { f => f(notice, component) }
        wasHovering = true
      case Moved if !nowHovering && wasHovering =>
        _onExit foreach { f => f(notice, component) }
        wasHovering = false
      case Dragged if nowHovering =>
        _onDrag foreach { f => f(notice, component) }
      case _ => ()
    }
  }
}
