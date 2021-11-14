package Graphics

import Component.Entity
import Component.TileMap.Tile
import Core.State.State
import _root_.Component.UI.Container.UIContainer
import _root_.Component.UI.{Border, UIComponent, UIText}

import java.awt.{BasicStroke, Font, Graphics, Graphics2D}

class Renderer(g: Graphics) {
  private val graphics = g.asInstanceOf[Graphics2D]
  def render(state: State): Unit = {
//    state.tileMap.tiles.values foreach renderTile
    renderEntities(state.entities)
    state.UI.children foreach { component => renderUI(component)}
  }

  private def renderUI(component: UIComponent): Unit = {
    graphics setColor component.background
    graphics fillRect (
      component.position.intX,
      component.position.intY,
      component.size.intW,
      component.size.intH
    )
    component.border foreach (b => renderUIBorder(b, component))
    component.text foreach (t => renderUIText(t, component))
    component match {
      case c: UIContainer => c.children foreach { child =>
        renderUI(child)
      }
      case _ => ()
    }
  }

  private def renderEntities(entities: Set[Entity]): Unit = {
    entities foreach { entity =>
      graphics setColor entity.color
      graphics fillRect (
        entity.position.intX,
        entity.position.intY,
        entity.size.intW,
        entity.size.intH
      )
    }
  }

  private def renderUIBorder(border: Border, component: UIComponent): Unit = {
    graphics.setColor(border.color)
    graphics.setStroke(new BasicStroke(border.thickness))
    graphics.drawRoundRect(
      component.position.intX - (border.thickness / 2),
      component.position.intY - (border.thickness / 2),
      component.size.intW + (border.thickness / 2),
      component.size.intH + (border.thickness / 2),
      border.rounding,
      border.rounding
    )
  }

  private def renderUIText(text: UIText, component: UIComponent): Unit = {
    graphics.setFont(new Font("Verdana", Font.BOLD, text.size))
    graphics.setColor(text.color)
    val aligned = text.align(component, graphics)
    graphics.drawString(
      text.text,
      aligned._1, aligned._2
    )
  }

  private def renderTile(tile: Tile): Unit = {
    graphics.drawImage(
      tile.sprite.image,
      tile.position.intX,
      tile.position.intY,
      null)
  }



}
