import ReleaseTransformations._

seq(lsSettings: _*)

name := "scandrill"

organization := "com.nitindhar"

description := "Scala client for Mandrill App"

scalaVersion := "2.11.5"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

resolvers += Resolver.typesafeRepo("releases")

parallelExecution in Test := true

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json"       % "2.3.10",
  "com.typesafe.play" %% "play-ws"         % "2.3.10",
  "com.typesafe.play" %% "play-functional" % "2.3.10",
  "org.scalatest"     %% "scalatest"       % "2.2.4"     % "test"
)

(LsKeys.tags in LsKeys.lsync) := Seq("mandrill", "app", "api", "scala", "client", "library", "async", "mailchimp")

(LsKeys.docsUrl in LsKeys.lsync) := Some(url("https://github.com/nitindhar7/scandrill"))

licenses := Seq("MIT" -> url("https://raw.githubusercontent.com/nitindhar7/scandrill/master/LICENSE"))

homepage := Some(url("https://github.com/nitindhar7/scandrill"))

publishArtifact in Test := false

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  ReleaseStep(action = Command.process("publishSigned", _)),
  setNextVersion,
  commitNextVersion,
  ReleaseStep(action = Command.process("sonatypeReleaseAll", _)),
  pushChanges
)

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
