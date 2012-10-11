name := "Dzikka"

organization := "dzikka"

version := "0.1"

scalaVersion := "2.9.2"


resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"


libraryDependencies += "com.typesafe.akka" % "akka-actor" % "2.0.3"

libraryDependencies += "org.scalatest" %% "scalatest" % "1.6.1" % "test"

libraryDependencies += "org.jsoup" % "jsoup" % "1.7.1"
