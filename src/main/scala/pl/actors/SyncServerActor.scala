package pl.actors

import akka.actor.Actor
import org.slf4j.{Logger, LoggerFactory}
import pl.filters.SyncMedianFilter
import pl.utils.Timer

class SyncServerActor extends Actor {
  val log: Logger = LoggerFactory.getLogger(this.getClass)

  override def receive: Receive = {
    case StartSyncFilter(fileReader, clientActor) =>
      val lastSender = sender()
      val timer: Timer = new Timer()
      log.debug(lastSender.toString())

      log.info("Creating Sync Median Filter...")
      val syncMedianFilter: SyncMedianFilter = new SyncMedianFilter()

      log.info("Applying Sync Median Filter...")
      timer.start()
      val filteredImage = syncMedianFilter.medianFilter(fileReader.getBufferedImage)
      timer.endTimer()
      log.info("Finished applying Sync Median Filter...")

      log.info("Saving Sync Image...")
      fileReader.saveImage(filteredImage, "serial")
      log.info("Sync Image Saved...")

      clientActor ! ShowResult(fileReader, timer, isAsync = false)

    case _ =>
      log.error("Unexpected Message")
      context.parent ! RestartProcess
  }
}
