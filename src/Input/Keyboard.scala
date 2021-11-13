package Input

import Observation.{KeyNotice, Publisher}

import java.awt.event.{KeyEvent, KeyListener}

object Keyboard extends KeyListener with Publisher[KeyNotice] {

  private var pressed = Array.fill(255)(false)

  override def keyTyped(e: KeyEvent): Unit = ()

  override def keyPressed(e: KeyEvent): Unit =
    publish(KeyNotice(press(e.getKeyCode)))

  override def keyReleased(e: KeyEvent): Unit =
    publish(KeyNotice(release(e.getKeyCode)))

  private def press(code: Int): Array[Boolean] = {
    pressed(code) = true
    pressed
  }

  private def release(code: Int): Array[Boolean] = {
    pressed(code) = false
    pressed
  }
}
