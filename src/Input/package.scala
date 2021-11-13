import java.awt.event.KeyEvent
package object Input {

  trait InputFlag
  trait MouseFlag
  case object Clicked extends MouseFlag
  case object Moved extends MouseFlag
  case object Dragged extends MouseFlag

  trait KeyFlag
  def KeyFlag(code: Int): KeyFlag = code match {
    case KeyEvent.VK_W => W
    case KeyEvent.VK_S => S
    case KeyEvent.VK_A => A
    case KeyEvent.VK_D => D
    case _ => Unknown
  }
  case object W extends KeyFlag
  case object S extends KeyFlag
  case object A extends KeyFlag
  case object D extends KeyFlag
  case object Unknown extends KeyFlag



}
