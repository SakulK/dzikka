package dzikka

import akka.actor._
import akka.event.Logging

trait Logger { this: Actor =>

	val log = Logging(context.system, this)
}