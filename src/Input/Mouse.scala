package Input

import Core.{CLOCK_UPS, Launcher}
import Observation.{MouseEventConversion, MouseNotice, Publisher}

import java.awt.event.{MouseAdapter, MouseEvent, MouseMotionListener}

object Mouse extends MouseAdapter
  with MouseMotionListener
  with Publisher[MouseNotice]
{

  private val MAX_MOVE_NOTICES_A_SECONDS = CLOCK_UPS
  private var lastMoveNoticeTime = Launcher.current

  override def mouseMoved(e: MouseEvent): Unit = {
    if(Launcher.current - lastMoveNoticeTime > MAX_MOVE_NOTICES_A_SECONDS) {
      val notice = e.toMouseNotice(Moved)
      publish(notice)
      lastMoveNoticeTime = Launcher.current
    }
  }

  override def mouseDragged(e: MouseEvent): Unit = {
    val notice = e.toMouseNotice(Dragged)
    publish(notice)
  }

  override def mouseClicked(e: MouseEvent): Unit = {
    val notice = e.toMouseNotice(Clicked)
    publish(notice)
  }
}
