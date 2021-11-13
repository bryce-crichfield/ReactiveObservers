package Component.TileMap

import Component.Interactable.MouseInteractable
import Component.Sprite.SpriteLoader.SPRITE_DIRECTORY
import Component.Sprite.{SpriteLoader, Spritesheet}
import Component.Component
import Observation.MouseNotice

import scala.collection.mutable

class TileMap extends Component with MouseInteractable[TileMap] {
  val spritesheet: Spritesheet = SpriteLoader.loadSpritesheet(SPRITE_DIRECTORY).getOrElse(Spritesheet(List()))
  val tiles: mutable.HashMap[Component.Position, Tile] = {
    val list = new mutable.HashMap[Component.Position, Tile]()
    for(x <- 0 until WIDTH) {
      for (y <- 0 until HEIGHT) {
        val sprite = spritesheet.sprites.head
        val tile = Tile(sprite)
        tile position (x * 64, y * 64)
        tile size (64, 64)
        list.put(tile.position, tile)
      }
    }
    list
  }

  // TODO: trying to enforce the tilemap covers screen, could be cleaner for sure
  this position (0, 0)
  this size(1280, 720)

  var current = 1;
  // once a tile has been changed it can no longer be changed
  this onClick { (notice: MouseNotice, map: TileMap) =>
    val tile = tiles.values.filter(t => notice.p.collides(t.bounds)).head
    val t2 = Tile(spritesheet.sprites(current))
    t2 position tile.position
    tiles.put(tile.position, t2)
    current += 1
  }

}
