package Core.State

import Observation.{Publisher, UpdateNotice}

object StateManager extends Publisher[UpdateNotice] {

  private var current: State = new StartState

  def state: State = current

  def update(): Unit = {
    publish(UpdateNotice(state))
  }

  def transition(state: State): Unit = {
    // it might be necessary to do some cleanup/bookkeeping here idk yet
    current = state
    // this might be excessive, but I am assuming it will resolve any emergent quirks?
    update()
  }
}
