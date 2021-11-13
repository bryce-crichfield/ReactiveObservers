import java.awt.Rectangle

package object Component {

  case class Position(x: Double, y: Double) {
    def collides(bounds: Rectangle): Boolean = {
      val horizontal = between(bounds.x, x, bounds.width)
      val vertical = between(bounds.y, y, bounds.height)
      vertical && horizontal
    }
    def +(x2: Double, y2: Double): Position = {
      Position(x + x2, y + y2)
    }

    def +(v: Position): Position = {
      Position(x + v.x, y + v.y)
    }

    def -(x2: Double, y2: Double): Position = {
      Position(x - x2, y - y2)
    }

    def *(s: Double): Position = {
      Position(x * s, y * s)
    }

    def *(v: Position): Position = {
      Position(
        x * v.x,
        y * v.y
      )
    }

    def *(m: Matrix): Position = {
      Position(
        x * m.v1.x + x * m.v1.y,
        y * m.v2.x + y * m.v2.y)
    }

    def /(s: Double): Position = {
      Position(x / s, y / s)
    }

    def intX: Int = {
      Math.round(x).toInt
    }

    def intY: Int = {
      Math.round(y).toInt
    }

  }
  object Position {
    def apply(x: Double, y: Double) = new Position(x, y)
  }


  case class Size(w: Double, h: Double) {
    def intW: Int =
      Math.round(w).toInt
    def intH: Int =
      Math.round(h).toInt
    def toInt: Size =
      Size(Math.round(w).toInt, Math.round(h).toInt)
  }


  case class Matrix(v1: Position, v2: Position)


  implicit class DoubleOp(d: Double) {
    def round(): Int = Math.round(d).toInt
  }

  def between(a: Double, b: Double, c: Double): Boolean = {
    a < b && b < c
  }











}
