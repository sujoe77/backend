package com.zs.prime

import akka.actor.ActorSystem
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.{Http, HttpConnectionContext}
import akka.stream.{ActorMaterializer, Materializer}
import com.typesafe.config.ConfigFactory
import com.zs.prime.grpc.{PrimeServiceHandler, PrimeServiceSImpl}

import scala.concurrent.{ExecutionContext, Future}


object PrimeServerS {
  def main(args: Array[String]): Unit = {
    val conf = ConfigFactory
      .parseString("akka.http.server.preview.enable-http2 = on")
      .withFallback(ConfigFactory.defaultApplication())
    val system = ActorSystem("PrimeServerS", conf)
    new PrimeServerS(system).run()
  }
}

class PrimeServerS(system: ActorSystem) {
  def run(): Future[Http.ServerBinding] = {
    implicit val sys: ActorSystem = system
    implicit val mat: Materializer = ActorMaterializer()
    implicit val ec: ExecutionContext = sys.dispatcher

    val service: HttpRequest => Future[HttpResponse] =
      PrimeServiceHandler(new PrimeServiceSImpl())

    val binding = Http().bindAndHandleAsync(
      service,
      interface = "127.0.0.1",
      port = 10001,
      connectionContext = HttpConnectionContext())

    binding.foreach { binding => println(s"gRPC server bound to: ${binding.localAddress}") }

    binding
  }
}

