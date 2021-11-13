package Component.UI

import Component.Component
import _root_.Component.UI.Container.UIContainer

import java.awt.Color

trait UIComponent extends Component {

  // [ FIELDS ] --------------------------------------------------------------------
  protected var _parent: Option[UIContainer] = None
  protected var _border: Option[Border] = Some(Border(Color.BLACK, 4, 10))
  protected var _margin: UISpacing = UISpacing(0,0,0,0)
  protected var _padding: UISpacing = UISpacing(0,0,0,0)
  protected var _background: Color = Color.RED
  protected var _text: Option[UIText] = Some(UIText("", 20))

  // [ GETTERS ] -------------------------------------------------------------------
  def background: Color = _background
  def parent: Option[UIContainer] = _parent
  def border: Option[Border] = _border
  def text: Option[UIText] =_text
  def margin: UISpacing = _margin
  def padding: UISpacing = _padding

  // [ SETTERS ] -------------------------------------------------------------------

  def parent(component: UIContainer) =
    _parent = Some(component)
  def background(color: Color): Unit =
    _background = color
  def border(color: Color): Unit =
    _border = _border map {b => b.copy(color = color)}
  def text(text: String): Unit =
    _text = _text map (t => t.copy(text = text))
  def text(color: Color): Unit =
    _text = _text map (t => t.copy(color = color))
  def margin(t: Int, r: Int, b: Int, l: Int): Unit =
    _margin = UISpacing(t, r, b, l)
  def padding(t: Int, r: Int, b: Int, l: Int): Unit =
    _padding = UISpacing(t, r, b, l)

  // [ METHODS ] -------------------------------------------------------------------
  def update(): Unit

}

