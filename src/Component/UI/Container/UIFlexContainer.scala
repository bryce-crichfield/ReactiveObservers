package Component.UI.Container

class UIFlexContainer(protected var _strategy: FlexStrategy) extends UIContainer {

  // [ FIELDS ] -------------------------------------------------------------------
  // Dictate the children's positions around the opposite axis to the strategy
  protected var _alignment: Alignment = Center
  // Determine if a Flex Container's Strategy should mold to its children around its core dimension
  // ie. if the container is vertical/horizontal, the height/width will adjust to the tallest/widest children
  protected var _sizingMode: SizingMode = Automatic


  // [ GETTERS ] -------------------------------------------------------------------
  def sizingMode: SizingMode = _sizingMode
  def strategy: FlexStrategy = _strategy
  def alignment: Alignment = _alignment

  // [ SETTERS ] -------------------------------------------------------------------
  def sizingMode(mode: SizingMode): Unit = _sizingMode = mode
  def strategy (flex: FlexStrategy): Unit = _strategy = flex
  def alignment (alignment: Alignment): Unit = _alignment = alignment

  // [ METHODS ] -------------------------------------------------------------------
  // Called to apply the flex and alignment to the children
  def update(): Unit = {
    strategy apply this
    alignment apply this
  }
}

