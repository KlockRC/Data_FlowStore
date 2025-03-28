name := "csv-to-json-api"
version := "1.0"
scalaVersion := "3.3.5"
resolvers += "Akka library repository".at("https://repo.akka.io/maven")
val AkkaVersion = "2.10.0"
val AkkaHttpVersion = "10.7.0"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",
  "ch.qos.logback" % "logback-classic" % "1.2.11"
)
