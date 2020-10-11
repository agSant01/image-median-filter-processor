package pl.actors

import akka.actor.Actor
import org.slf4j.{Logger, LoggerFactory}
import pl.filters.AsyncMedianFilter
import pl.utils.Timer

class AsyncServerActor extends  Actor {
  val log: Logger = LoggerFactory.getLogger(this.getClass)

  override def receive: Receive = {
    case StartAsyncFilter(fileReader, clientActor) =>
      val timer: Timer = new Timer()

      log.info("Creating Async Median Filter...")
      val asyncMedianFilter: AsyncMedianFilter = new AsyncMedianFilter()

      log.info("Applying Async Median Filter...")
      timer.start()
      val filteredImage = asyncMedianFilter.medianFilter(fileReader.getBufferedImage)
      timer.endTimer()
      log.info("Finished applying Async Median Filter...")

      log.info("Saving Async Image...")
      fileReader.saveImage(filteredImage, "parallelized")
      log.info("Async Image Saved...")

      clientActor ! ShowResult(fileReader, timer, isAsync = true)

    case _ =>
      log.error("Unexpected Message")
      context.parent ! RestartProcess
  }

}
