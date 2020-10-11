package pl

import akka.actor.{ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import pl.actors.{AskForFile, ClientActor}

import scala.language.postfixOps

object Main {
  def main(args: Array[String]): Unit = {
    // load configuration into the system
    val config = ConfigFactory.load()

    // create actor system with config
    val actorSystem = ActorSystem("ActorSystem", config.getConfig("myapp").withFallback(config))

    // create client Actor
    val client: ActorRef = actorSystem.actorOf(Props[ClientActor], "ClientActor")

    // start process
    client ! AskForFile
  }
}