package Graphics

import Core.State
import Input.{Keyboard, Mouse}

import java.awt.image.BufferStrategy
import java.awt.{Canvas, Color, Dimension, Graphics}
import javax.swing.JFrame

class Display(val w: Int = DEFAULT_WIDTH, val h: Int = DEFAULT_HEIGHT)
  extends JFrame  {
  addWindowListener(WindowAdapter)
  setSize(w, h)
  setResizable(false)
  val canvas = new Canvas()
  canvas.setFocusable(false)
  canvas.setPreferredSize(new Dimension(w, h))
  canvas.addMouseListener(Mouse)
  canvas.addMouseMotionListener(Mouse)
  add(canvas)
  addKeyListener(Keyboard)
  pack()
  canvas.createBufferStrategy(2)
  setLocationRelativeTo(null)
  setVisible(true)

  def render(state: State): Unit = {
    val bs = bufferStrategy()
    val graphics: Graphics = bs.getDrawGraphics
    val renderer = new Renderer(graphics)
    graphics.setColor(Color.WHITE)
    graphics.fillRect(0,0,w,h)
    renderer.render(state)
    graphics.dispose()
    bs.show()
  }


  def bufferStrategy(): BufferStrategy = {
    if(canvas.getBufferStrategy == null) {
      canvas.createBufferStrategy(2)
      canvas.getBufferStrategy
    } else {
      canvas.getBufferStrategy
    }
  }


}
