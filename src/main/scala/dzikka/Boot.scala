package dzikka

import akka.util.duration._

object Boot extends App with BasicCrawler {

	val seed = new java.net.URL("http://forum.dobreprogramy.pl/")

	downloader ! Download(seed)

	system.scheduler.scheduleOnce(30 second) {
		system.shutdown
	}
}