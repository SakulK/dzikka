package dzikka

import akka.actor._

trait StatsGathererComponent { this: IndexerComponent with LinksExtractorComponent =>

	def statsGatherer: ActorRef

	class StatsGatherer extends Actor with Logger {
		
		var startup = System.currentTimeMillis
		var total = 0
		var success = 0
		var error = 0

		def receive = {
			case page: Page =>
				total += 1
				if(page.status == 200) {
					success += 1
					linksExtractor ! page
					indexer ! page
				} else {
					error += 1
				}
			case PrintStats =>
				log.info("***** Dzikka Stats ******")
				log.info("	Uptime: " + uptime  + "s")
				log.info("	Total requests: " + total)
				log.info("	Successes: " + success)
				log.info("	Errors: " + error)
				log.info("	Avg speed: " + avgSpeedPerSec + "/s")
		}

		def uptime = (System.currentTimeMillis - startup) / 1000

		def avgSpeedPerSec = (total / uptime)
	}
}