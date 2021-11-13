package Observation

trait Publisher[N <: Notice] {
  private var subscribers = Set[Subscriber[N]]()
  def subscribe(subscriber: Subscriber[N]): Unit =
    subscribers += subscriber
  def unsubscribe(subscriber: Subscriber[N]): Unit =
    subscribers -= subscriber
  def publish(notice: N): Unit =
    subscribers.foreach(_.receive(notice))
}
