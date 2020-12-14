lazy val akkaHttpVersion = "10.2.2"
lazy val akkaVersion = "2.6.10"

lazy val akkaGrpcVersion = "1.0.2"

enablePlugins(AkkaGrpcPlugin)

akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Java)

autoScalaLibrary := false

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.zs",
      scalaVersion := "2.13.1",
      name := "rest-akka"
    )),
    name := "rest-akka",
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
      "com.novocode" % "junit-interface" % "0.10" % Test
    ),

    testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")
)
