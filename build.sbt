import SonatypeKeys._

sonatypeSettings

name := "scandrill"

organization := "com.nitindhar"

version := "0.0.1"

description := "Scala client for Mandrill App"

scalaVersion := "2.11.5"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers += Resolver.typesafeRepo("releases")

parallelExecution in Test := true

libraryDependencies ++= Seq(
   "com.typesafe.play" % "play-json_2.11" % "2.4.3"
)

publishArtifact in Test := false

publishMavenStyle := true

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/nitindhar7/scandrill</url>
  <licenses>
    <license>
      <name>The MIT License</name>
      <url>https://opensource.org/licenses/MIT</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:github.com/nitindhar7/scandrill.git</connection>
    <developerConnection>scm:git:git@github.com:nitindhar7/scandrill.git</developerConnection>
    <url>https://github.com/nitindhar7/scandrill</url>
  </scm>
  <developers>
    <developer>
      <id>nitindhar7</id>
      <name>Nitin Dhar</name>
      <url>http://nitindhar.com</url>
    </developer>
  </developers>
)
