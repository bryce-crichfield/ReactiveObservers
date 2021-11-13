package Component.Sprite

import java.awt.Image
import java.io.File
import javax.imageio.ImageIO
import scala.util.{Success, Try}

object SpriteLoader {

  val SPRITE_DIRECTORY = "/home/bryce/IdeaProjects/System_02/resources/sprites"

  def loadSprite(name: String): Try[Sprite] = {
    for {
      file <- loadFile(SPRITE_DIRECTORY + name)
      image <- loadImage(file)
    } yield Sprite(image)
  }
  def loadFile(path: String): Try[File] = Try(new File(path))
  def loadImage(file: File): Try[Image] = Try(ImageIO.read(file))

  def loadImages(files: List[File]): Try[List[Image]] = Try {
    files map {file => ImageIO.read(file) }
  }

  def loadSprites(images: List[Image]): Try[List[Sprite]] = Try {
    images map {image => Sprite(image)}
  }

  def loadDirectory(directory: File): Try[List[File]] = {
    if(directory.isDirectory) Success(directory.listFiles().toList)
    else util.Failure(new RuntimeException(directory.getAbsolutePath + " is not directory"))
  }

  def loadSpritesheet(path: String): Option[Spritesheet] = {
    val tried = for {
      directory <- loadFile(path)
      files <- loadDirectory(directory)
      images <- loadImages(files)
      sprites <- loadSprites(images)
    } yield Spritesheet(sprites)
    tried.toOption
  }


}
