name := "PLAssignment4"

version := "0.1"

scalaVersion := "2.13.3"

val akkaVersion    = "2.6.9"

libraryDependencies.++=(Seq(
  "com.typesafe.akka" %% "akka-actor-typed"         % akkaVersion,
  "com.typesafe.akka" %% "akka-stream"              % akkaVersion,
  "ch.qos.logback"    % "logback-classic"            % "1.2.3",
  "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0",
  "org.scalactic"     %% "scalactic"                % "3.2.0",
  "org.scalatest"     %% "scalatest"                % "3.2.0"         % Test
))
