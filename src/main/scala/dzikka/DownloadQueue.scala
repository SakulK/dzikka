package dzikka

import akka.actor._
import akka.util.duration._

trait DownloadQueueComponent { this: DownloaderComponent =>
	
	def downloadQueue: ActorRef
	def kindess: Int

	class DownloadQueue extends Actor with Logger {

		def receive = {
			case Links(links) => 
				links.foreach { link =>
					context.system.scheduler.scheduleOnce(kindess milliseconds, downloader, Download(link))
				}
		}
	}
}