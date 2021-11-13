package Component

import Component.Interactable.KeyInteractable
import Input.{A, D, S, W}

class Player extends Entity
  with KeyInteractable[Component]
{
  this.position(Position(20, 20))
  this.size(Size(50, 50))

  val transporter = new Transporter[Player](this)
  onPress {
    case W => _ => transporter.move(Up)
    case S => _ => transporter.move(Down)
    case D => _ => transporter.move(Right)
    case A => _ => transporter.move(Left)
  }
}
