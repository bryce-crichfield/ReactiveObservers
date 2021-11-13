import Component.Position
import Core.State
import Input.MouseFlag

import java.awt.event.MouseEvent

package object Observation {

  trait InputNotice extends Notice

  case class MouseNotice(p: Position, flag: MouseFlag) extends InputNotice
  case class KeyNotice(pressed: Array[Boolean]) extends InputNotice

  implicit class MouseEventConversion(e: MouseEvent) {
    def toMouseNotice(flag: MouseFlag): MouseNotice =
      MouseNotice(Position(e.getX, e.getY), flag)
  }

  // Todo: Right now this might be redundant
  case class UpdateNotice(state: State) extends Notice

  // Todo: This should be published by something global
  // it might be appropriate for it to happen at any time
  case class AnimationNotice(time: Long) extends Notice
}
