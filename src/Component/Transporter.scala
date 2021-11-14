package Component

import Core.State.StateManager
import Observation.{Subscriber, UpdateNotice}

// TODO: This will define general locomotion for a class
// - direction
// - speed
// - motion
// - reposition

sealed class Transporter[C <: Component](component: Component) extends
  Subscriber[UpdateNotice] {
  // TODO: ensure that this will work
  StateManager subscribe this
  var directions: Set[Direction] = Set(Left)
  var speed: Double = 2
  var acceleration: Double = 1

  def applyMotion: Unit = {
    val direction = directions.map(_.vector).reduce(_ + _)
    val movement = direction * (speed * acceleration)
    val position = component.position + movement
    component position (position.x, position.y)
  }

  override def respond(notice: UpdateNotice): Unit = {
    if(directions.nonEmpty) {
      println(directions)
      applyMotion
    }
    directions = Set()
  }

  def move(direction: Direction): Unit = {
    directions += direction
  }
}



trait Direction {
  val vector: Position
}
case object Up extends Direction {
  val vector = Position(0, -1)
}
case object Down extends Direction {
  val vector = Position(0, 1)
}
case object Left extends Direction {
  val vector = Position(-1, 0)
}
case object Right extends Direction {
  val vector = Position(1, 0)
}