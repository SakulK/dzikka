package dzikka

import akka.actor._
import java.net._
import scala.collection.JavaConverters._

trait LinksExtractorComponent { this: DownloadQueueComponent =>

	def linksExtractor: ActorRef

	class LinksExtractor extends Actor with Logger {

		def receive = {
			case Page(url, _, Some(document)) =>
				val links = document.select("a[href]").asScala.flatMap { element =>
					try {
						Some(new URL(element.attr("abs:href")))
					} catch {
						case e: MalformedURLException => None
					}
				}
				downloadQueue ! Links(Set() ++ links)
		}
	}
}