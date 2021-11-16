package Component.UI.Container

import Component.{Position, Size}
import Component.UI.Container.Absolute
import Component.UI.UIComponent

import java.awt.Color

class UIGridContainer(protected var _gridsize: Size) extends UIContainer
{
  // [ FIELDS ]  -----------------------------------------------------------------------
  protected var _sizingMode: SizingMode = Absolute
  _children = List.fill(gridsize.intW) {
    val horizontals = List.fill(gridsize.intH) {
      new UIFlexContainer(Horizontal) {
        this sizingMode _sizingMode
        this alignment Center
      }
    }
    new UIFlexContainer(Vertical) {
      this sizingMode _sizingMode
      horizontals foreach(h => this.add(h))
      this alignment Center
    }
  }
  // [ GETTERS ] -----------------------------------------------------------------------
  def gridsize: Size = _gridsize

  // [ SETTERS ] -----------------------------------------------------------------------
  def gridsize(size: Size): Unit = _gridsize = size

  // I am really not a huge fan of these update calls, however;
  // assuming the sizing is absolute, we need to cascade changes
  override def size(s: Size): Unit = {
    _size = s
    update()
  }

  override def size(s: Int): Unit = {
    _size = Size(s)
    update()
  }

  override def background(color: Color): Unit = {
    val set = (c: UIComponent) => c background color
    _background = color
    cascade(set)
  }

  override def border(pixels: Int, color: Color): Unit = {
    val setter = (c: UIComponent) => c border(pixels, color)
    cascade(setter)
  }

  // [ METHODS ] -----------------------------------------------------------------------
  override def update(): Unit = {
    val gridwidth = (_size.w / gridsize.w).toInt
    val gridheight = (_size.h / gridsize.h).toInt
    val setter = (c: UIComponent) => c size Size(gridwidth, gridheight)
    cascade(setter)
    children foreach {_.size(Size(gridwidth, _size.h))}
    reposition()
  }

  // takes a setter function and cascades that change to all children
  protected def cascade[A](change: UIComponent => Unit): Unit = {
    children foreach { child =>
      child.asInstanceOf[UIFlexContainer]
        .children.foreach {grid =>
          change(grid)
      }
      change(child)
    }
  }

  // We can not effectively allow unqualified addition, therefore default to Unit
  override def add(component: UIComponent): Unit = ()
  // This is the add we should be using
  def add(component: UIComponent, x: Int, y: Int): Unit = {
    val xth = _children(x).asInstanceOf[UIFlexContainer]
    xth.swap(y, component)
  }

  protected def reposition(): Unit = {
    val x = _position.x
    val y = _position.y
    val width = _size.w / _gridsize.w
    val height = _size.h / _gridsize.h
    for(xth <- _children.indices) {
      val child = _children(xth).asInstanceOf[UIContainer]
      for(yth <- child.children.indices) {
        val position = Position(x + (xth * width), y + (yth * height))
        child.children(yth) position(position)
      }
      val position = Position(x + (xth * width), y + height)
      child.position(position)
    }
  }

  def access(x: Int, y: Int): UIFlexContainer =
    _children(x).asInstanceOf[UIFlexContainer].child(y).asInstanceOf[UIFlexContainer]

}
