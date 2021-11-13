package Graphics

import java.awt.event.{WindowAdapter, WindowEvent}


object WindowAdapter extends WindowAdapter {

  override def windowClosing(e: WindowEvent): Unit =
    System.exit(0)
}
