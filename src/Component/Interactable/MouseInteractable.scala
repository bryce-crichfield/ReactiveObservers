package Component.Interactable

import Component.Component
import Observation.MouseNotice

trait MouseInteractable[C <: Component] {
  this: C =>
  private val interactor = new MouseInteractor[C](this)

  def onClick(f: (MouseNotice, C) => Unit): Unit = interactor._onClick = Some(f)

  def onEnter(f: (MouseNotice, C) => Unit): Unit = interactor._onEnter = Some(f)

  def onExit(f: (MouseNotice, C) => Unit): Unit = interactor._onExit = Some(f)

  def onClick(f: => Unit): Unit = interactor._onClick = Some((_: MouseNotice, _: C) => f)

  def onEnter(f: => Unit): Unit = interactor._onEnter = Some((_: MouseNotice, _: C) => f)

  def onExit(f: => Unit): Unit = interactor._onExit = Some((_: MouseNotice, _: C) => f)

  def onEnter(f: C => Unit): Unit = interactor._onEnter = Some((_: MouseNotice, c: C) => f(c))
  def onExit(f: C => Unit): Unit = interactor._onExit = Some((_: MouseNotice, c: C) => f(c))
  def onClick(f: C => Unit): Unit = interactor._onClick = Some((_: MouseNotice, c: C) => f(c))

  // i wish this could be placed elsewhere, but whatever
  // this allows us to turn the interactor (listener on/off without unsubscribing)
  def active (set: Boolean): Unit =
    interactor active set

  def active: Boolean =
    interactor.active
}
