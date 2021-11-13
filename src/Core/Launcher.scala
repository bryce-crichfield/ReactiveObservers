package Core

import Graphics.Display

object Launcher extends Clock {
  val display = new Display
  loop(display)
}
