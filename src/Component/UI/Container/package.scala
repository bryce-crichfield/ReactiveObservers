package Component.UI

import Component.{Position, Size}

package object Container {
    // [ ALIGNERS ] --------------------------------------------------------------------
    // An operation to provide the aligned position of the children of a flex container
    // The X value applies only to the Horizontal Strategy, and Y value applies only to the Vertical
    private type AlignmentPosition = UIFlexContainer => UIComponent => Position
    private val CenterAlignedPosition: AlignmentPosition = container => { child =>
      val x = container.size.w - (child.size.w + child.margin.horizontal)
      val y = container.size.h - (child.size.h + child.margin.vertical)
      Position(child.position.x + (x / 2), child.position.y + (y / 2))
    }
    private val LeftAlignedPosition: AlignmentPosition = container => { child =>
      val x = child.margin.left + container.padding.left + child.position.x
      val y = child.margin.top + container.padding.top + child.position.y
      Position(x, y)
    }
    private val RightAlignedPosition: AlignmentPosition = container => { child =>
      val x = container.bounds.getWidth - container.padding.right - child.margin.right - child.size.w
      val y = container.bounds.getHeight - container.padding.bottom - child.margin.bottom - child.size.h
      Position(x, y)
    }
    // ---------------------------------------------------------------------------------

    // [ ALIGNMENTS ] ------------------------------------------------------------------
    // An alignment will reposition the children of a flex container around the subdominant axis
    type Alignment = UIFlexContainer => Unit
    val Center: Alignment = container => applyAlignment(CenterAlignedPosition)(container)
    val Left: Alignment = container => applyAlignment(LeftAlignedPosition)(container)
    val Right: Alignment = container => applyAlignment(RightAlignedPosition)(container)
    val Top: Alignment = container => applyAlignment(LeftAlignedPosition)(container)
    val Bottom: Alignment = container => applyAlignment(RightAlignedPosition)(container)
    // Applies an alignment to repositioning to a container
    // Ensures that the alignment will apply to the subdominant axis
    private val applyAlignment: AlignmentPosition => UIFlexContainer => Unit = position => { container =>
      container.children.foreach { child =>
        val aligned = position(container)(child)
        container.strategy match {
          case Horizontal =>
            child position (child.position.x, aligned.y)
          case Vertical =>
            child position (aligned.x, child.position.y)
        }
      }
    }
    // ---------------------------------------------------------------------------------

    // [ SIZE RECALCULATION ] ----------------------------------------------------------
    //  determines the size of a flex container to snap it to the combined size of its children
    type ContentSizeCalculator = UIFlexContainer => Size
    private val CalculateContainerSize: (UIFlexContainer, ContentSizeCalculator) => Size = { (container, calculator) =>
      val w = container.padding.horizontal + calculator(container).w
      val h = container.padding.vertical + calculator(container).h
      Size(w, h)
    }
    private val VerticalContentSizeCalculator: ContentSizeCalculator = { container =>
      if(container.children.isEmpty) container.size
      else {
        val totalChildHeight = container.children.foldLeft(0d) { (acc, comp) =>
          acc + (comp.size.h + comp.margin.vertical)
        }
        val widestChild = container.children.maxBy(_.size.w).size.w
        Size(widestChild, totalChildHeight)
      }
    }
    private val HorizontalContentSizeCalculator: ContentSizeCalculator = { container =>
      if(container.children.isEmpty) container.size
      else {
        println(container.children.size)
        val totalChildWidth = container.children.foldLeft(0d) { (acc, comp) =>
          acc + (comp.size.w + comp.margin.horizontal)
        }
        val tallestChild = container.children.maxBy(_.size.h).size.h
        Size(totalChildWidth, tallestChild)
      }
    }
    // ---------------------------------------------------------------------------------

    // [ CONTENT REPOSITIONER ] --------------------------------------------------------
    // will move the children of a flex container to their appropriate place
    type ContentRepositioner = UIFlexContainer => Unit
    private val HorizontalContentRepositioner: ContentRepositioner = { container =>
        if(container.children.isEmpty) ()
        else {
          var modifier = container.padding.left
          container.children foreach { child =>
            modifier += child.margin.left
            child position (child.position.x + modifier, child.position.y)
            modifier += child.size.w.toInt
            modifier += child.margin.right
          }
        }
      }
    private val VerticalContentRepositioner: ContentRepositioner = { container =>
      if(container.children.isEmpty) ()
      else {
        var modifier = container.padding.top
        container.children foreach { child =>
          modifier += child.margin.top
          child position (container.position.x, container.position.y + modifier)
          modifier += child.size.h.toInt
          modifier += child.margin.bottom
        }
      }
    }
    // ---------------------------------------------------------------------------------

    // [ STRATEGIES ] ------------------------------------------------------------------
    // responsible for ensuring that components are equally spaced along either X or Y dimension
    // composed of a size recalculation (to snap the container to the combined size of the children)
    // composed of a operation to reposition the children along the desired dimension
    type FlexStrategy = UIFlexContainer => Unit
    type FlexApplicator = ContentSizeCalculator => ContentRepositioner => UIFlexContainer => Unit
    private val FlexApplicator: FlexApplicator = calculator => repositionContents => { container =>
      val snap = CalculateContainerSize(container, calculator)
      container.sizingMode match {
        case Automatic => container.size(snap.w, snap.h)
        case Absolute => container.size(container.size.w, snap.h)
      }
      repositionContents(container)
    }
    val Vertical: FlexStrategy = FlexApplicator(VerticalContentSizeCalculator)(VerticalContentRepositioner)
    val Horizontal: FlexStrategy = FlexApplicator(HorizontalContentSizeCalculator)(HorizontalContentRepositioner)
    // ---------------------------------------------------------------------------------

    trait SizingMode
    case object Absolute extends SizingMode
    case object Automatic extends SizingMode

}
