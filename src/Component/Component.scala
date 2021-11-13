package Component

import java.awt.Rectangle
import scala.language.implicitConversions
trait Component {

  // [ FIELDS ] --------------------------------------------------------------------
  protected var _position: Position = Position(0, 0)
  protected var _size: Size = Size(0, 0)

  // [ GETTERS ] --------------------------------------------------------------------
  def position: Position = _position
  def size: Size = _size
  // returns the absolute position of the top left and bottom right corners of this component
  def bounds: Rectangle = {
    new Rectangle(
      _position.x.toInt,
      _position.y.toInt,
      _size.w.toInt + _position.x.toInt,
      _size.h.toInt + _position.y.toInt
    )
  }
  // [ SETTERS ] -------------------------------------------------------------------
  def position(x: Double, y: Double): Unit =
    _position = Position(x, y)
  def position (p: Position): Unit =
    _position = p
  def size(w: Double, h: Double): Unit =
    _size = Size(w, h)
  def size(s: Size): Unit =
    _size = s

  // [ METHODS ] --------------------------------------------------------------------
  def scale(px: Int, enlarge: Boolean): Unit = {
    val s = if(enlarge) px else -px
    position (position.x - (s / 2), position.y - (s / 2))
    size (size.w + s, size.h + s)
  }

}
