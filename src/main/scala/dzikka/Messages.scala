package dzikka

import java.net.URL
import org.jsoup.nodes.Document

case class Download(url: URL, encoding: String)

case class Page(url: URL, status: Int, document: Option[Document] = None)

case class Links(links: Set[URL])

case object PrintStats