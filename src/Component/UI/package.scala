package Component

import java.awt.Color

package object UI {

  case class Border(color: Color, thickness: Int, rounding: Int = 0)

  case class UISpacing(top: Int, right: Int, bottom: Int, left: Int) {
    def horizontal: Int = right + left
    def vertical: Int = top + bottom
  }

  object UISpacing {

    def apply(horizontal: Int, vertical: Int): UISpacing = {
      assert(horizontal >= 0 && vertical >= 0)
      UISpacing(vertical, horizontal, vertical, horizontal)
    }

    def apply(uniformSpacing: Int): UISpacing = {
      assert(uniformSpacing >= 0)
      UISpacing(uniformSpacing, uniformSpacing, uniformSpacing, uniformSpacing)
    }
  }


}
