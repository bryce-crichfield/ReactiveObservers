package Core

import Core.State.StateManager
import Graphics.Display

trait Clock extends App {

  def current: Long = System.currentTimeMillis()

  private var running = false

  def loop(display: Display): Unit = {
    running = true
    var nextTick = current
    while (running) {
      while (current > nextTick) {
        StateManager.update()
        nextTick += CLOCK_SKIP_TIME
        display.render(StateManager.state)
      }
    }
  }


}
