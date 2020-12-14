name := "rest-akka"

version := "1.0"
scalaVersion := "2.13.2"

val akkaHttpVersion = "10.2.2"
val akkaVersion = "2.6.10"

val akkaGrpcVersion = "1.0.2"

enablePlugins(AkkaGrpcPlugin)

akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Java)

autoScalaLibrary := false

crossPaths := false

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-discovery" % akkaVersion,
  "com.typesafe.akka" %% "akka-pki" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http2-support" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-jackson" % akkaHttpVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "net.jodah" % "failsafe" % "2.4.0",

  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "junit" % "junit" % "4.12" % Test,
  "com.novocode" % "junit-interface" % "0.10" % Test,
  "org.jooq" % "joor-java-8" % "0.9.13" % Test,
  "org.jmockit" % "jmockit" % "1.23" % Test,
  "com.typesafe.akka" %% "akka-cluster-typed" % "2.6.10" % Test,
  "org.scalactic" %% "scalactic" % "3.2.2",
  "org.scalatest" %% "scalatest" % "3.2.2" % Test
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")

