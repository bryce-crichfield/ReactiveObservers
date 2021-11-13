package Core

import Observation.{Publisher, UpdateNotice}

object StateManager extends Publisher[UpdateNotice] {

  private var current: State = new State {}

  def state: State = current

  def update(): Unit = {
    publish(UpdateNotice(state))
  }
}
