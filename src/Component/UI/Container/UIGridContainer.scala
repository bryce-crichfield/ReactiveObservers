package Component.UI.Container

import Component.Size

class UIGridContainer(protected var _gridsize: Size) extends UIContainer
{
  // [ FIELDS ]  -----------------------------------------------------------------------

  // [ GETTERS ] -----------------------------------------------------------------------
  def gridsize: Size = _gridsize

  // [ SETTERS ] -----------------------------------------------------------------------
  def gridsize(size: Size): Unit = _gridsize = size


  // [ METHODS ] -----------------------------------------------------------------------
  override def update(): Unit = ???

  protected def reposition(): Unit = ???
}
