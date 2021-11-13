package Component.UI

import Component.UI.UIText.{CenterHorizontal, CenterVertical, TextAlignment}

import java.awt.{Color, Graphics}

case class UIText(text: String, size: Int,
                  color: Color = Color.BLACK,
                  alignment: TextAlignment = (CenterHorizontal, CenterVertical))
{
  def align(component: UIComponent, graphics: Graphics): (Int, Int) = {
    (alignment._1(this, component, graphics), alignment._2(this, component, graphics))
  }
}

object UIText {
  // TODO: Add support for Left, Right, Top, and Bottom alignment
  // I am not sure if I really like this implementation
  // This is specifically only for the rendering of the text and requires a reference to the graphics
  // It could be easy to forgot this necessary reference to the graphics/call to reposition
  type TextAlignment = (HorizontalTextAlignment, VerticalTextAlignment)
  type HorizontalTextAlignment = (UIText, UIComponent, Graphics) => Int
  type VerticalTextAlignment = (UIText, UIComponent, Graphics) => Int

  val CenterHorizontal: HorizontalTextAlignment = { (text, component, graphics) =>
    val bounds = graphics.getFontMetrics().getStringBounds(text.text, graphics)
    Math.round(((component.size.w - bounds.getWidth) / 2) + component.position.x).toInt
  }

  val CenterVertical: VerticalTextAlignment = (text, component, graphics) => {
    val bounds = graphics.getFontMetrics().getStringBounds(text.text, graphics)
    val y = Math.round(((component.size.h - bounds.getHeight) / 2) + component.position.y).toInt
    y + (bounds.getHeight * .8).toInt
  }
}
