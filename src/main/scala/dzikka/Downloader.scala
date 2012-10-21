package dzikka

import akka.actor._
import org.jsoup._

trait DownloaderComponent { this: StatsGathererComponent =>

	def downloader: ActorRef

	class Downloader extends Actor with Logger {

		def receive = {
			case Download(url) => 
				log.info("Downloading url: " + url)
				try {
					val doc = Jsoup.connect(url.toString).get
					statsGatherer ! Page(url, 200, Some(doc))
				} catch {
					case e =>
						log.warning("Error " + e)
						statsGatherer ! Page(url, 500)
				}
		}
	}
}