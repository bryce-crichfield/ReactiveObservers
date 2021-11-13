package Component

import Observation.{Subscriber, UpdateNotice}

// takes an interval in milliseconds
// receives update notices from the state manager
// passes tick to the animator
trait AnimationTimer extends Subscriber[UpdateNotice]
{
  this: Animator =>
  private var last: Long = 0

  override def respond(notice: UpdateNotice): Unit = {
    val current = System.currentTimeMillis()
    if((current - last) > _interval) {
      tick()
      last = current
    }
  }
}
