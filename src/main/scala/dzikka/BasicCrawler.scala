package dzikka

import akka.actor._
import akka.util.duration._
import akka.routing._

trait BasicCrawler extends DownloaderComponent with 
		StatsGathererComponent with IndexerComponent with 
		LinksExtractorComponent with DownloadQueueComponent {

		val system = ActorSystem("Dzikka")
		val downloader = system.actorOf(Props(new Downloader).withRouter(FromConfig()), name = "downloader")
		val statsGatherer = system.actorOf(Props(new StatsGatherer), name = "statsGatherer")
		val indexer = system.actorOf(Props(new Indexer).withRouter(FromConfig()), name = "indexer")
		val linksExtractor = system.actorOf(Props(new LinksExtractor), name = "linksExtractor")
		val downloadQueue = system.actorOf(Props(new DownloadQueue), name = "downloadQueue")

		val kindess = 500

		system.scheduler.schedule(10 second, 10 second, statsGatherer, PrintStats)
}