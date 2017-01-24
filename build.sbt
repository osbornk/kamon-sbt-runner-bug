name := "kamon-sbt-runner-bug"

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-feature",
  "-language:reflectiveCalls",
  "-deprecation",
  "-unchecked",
  "-Xlint",
  "-Xfatal-warnings"
)

javacOptions += "-Xdoclint:none"

val kamonVersion = "0.6.5"
val aspectjVersion = "1.8.10"

libraryDependencies ++= Seq(
  "io.kamon" %% "kamon-core" % "0.6.6",
  "io.kamon" %% "kamon-scala" % kamonVersion,
  "io.kamon" %% "kamon-system-metrics" % kamonVersion
)

lazy val root = project in file(".")

resolvers += Resolver.bintrayRepo("kamon-io", "releases")
