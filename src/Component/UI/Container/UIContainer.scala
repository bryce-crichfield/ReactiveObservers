package Component.UI.Container

import Component.UI.UIComponent

trait UIContainer extends UIComponent {

  // [ FIELDS ] --------------------------------------------------------------------
  protected var _children: List[UIComponent] = List()

  // [ GETTERS ] -------------------------------------------------------------------
  def children: List[UIComponent] =
    _children

  // [ METHODS ] -------------------------------------------------------------------

  // add will reassign the parent of the passed component to be this container
  // an element may not have two parents, so this will enforce the tree structure
  // this should be the only place where a parent should ever be set
  // although technically, it is allowed that anyone may set the parent
  def add(component: UIComponent): Unit = {
    component.parent match {
      case Some(parent) =>
        parent.remove(component)
      case _ => ()
    }
    component parent this
    _children = _children :+ component
  }

  def remove(component: UIComponent): Unit = {
    _children = _children.filter(c => c != component)
  }

  def clear(): Unit =
    _children = List[UIComponent]()

}
