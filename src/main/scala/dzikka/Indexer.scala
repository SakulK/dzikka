package dzikka

import akka.actor._

trait IndexerComponent {

	def indexer: ActorRef

	class Indexer extends Actor with Logger {

		def receive = {
			case Page(url, _, Some(document)) =>
				log.info("Indexing page with url " + url)
		}
	}
}