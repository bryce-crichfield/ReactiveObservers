package Component

class Animator(protected var _interval: Int) extends AnimationTimer {

  protected var frame: Int = 0

  def tick(): Unit = ???

}
