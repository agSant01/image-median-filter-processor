package pl.actors

import akka.actor.ActorRef
import pl.utils
import pl.utils.FileReader

case class AskForFile() {}
case class StartSyncFilter(fileReader: FileReader, replyTo: ActorRef) {}
case class StartAsyncFilter(fileReader: FileReader, replyTo: ActorRef) {}
case class ShowResult(fileReader: FileReader, timer: utils.Timer, isAsync: Boolean) {}
case class LoadFile(filepath: String) {}
case class RestartProcess() {}
case class Terminate() {}
