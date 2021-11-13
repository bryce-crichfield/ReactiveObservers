package Observation

trait Subscriber[N <: Notice] {
  protected var _active = true
  def receive(notice: N): Unit =
    if(_active) respond(notice)
  def respond(notice: N): Unit

  def active (set: Boolean) = _active = set
  def active: Boolean = _active
}
