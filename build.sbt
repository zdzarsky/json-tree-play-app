name := "jsont-tree-play-app"

version := "1.0"

scalaVersion := "2.13.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += guice
libraryDependencies += "com.typesafe.play" %% "play-test" % "2.8.0-M5" % Test

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test