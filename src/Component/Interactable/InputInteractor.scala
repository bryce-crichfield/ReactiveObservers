package Component.Interactable

import Component.Component
import Observation.{InputNotice, Subscriber}

trait InputInteractor[N <: InputNotice, C <: Component] extends Subscriber[N]
