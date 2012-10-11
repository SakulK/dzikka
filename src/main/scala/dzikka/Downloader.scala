package dzikka

import akka.actor._
import scala.io.Source
import org.jsoup._

trait DownloaderComponent { this: StatsGathererComponent =>

	def downloader: ActorRef

	class Downloader extends Actor with Logger {

		def receive = {
			case Download(url, encoding) => 
				log.info("Downloading url: " + url)
				try {
					val content = Source.fromURL(url, encoding).mkString
					val doc = Jsoup.parse(content, url.toString)
					statsGatherer ! Page(url, 200, Some(doc))
				} catch {
					case e =>
						log.warning("Error " + e)
						statsGatherer ! Page(url, 500)
				}
		}
	}
}