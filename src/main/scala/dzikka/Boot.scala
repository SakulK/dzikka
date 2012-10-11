package dzikka

import akka.util.duration._

object Boot extends App with BasicCrawler {

	val seed = new java.net.URL("http://forum.android.com.pl")

	downloader ! Download(seed, "UTF-8")

	system.scheduler.scheduleOnce(30 second) {
		system.shutdown
	}
}