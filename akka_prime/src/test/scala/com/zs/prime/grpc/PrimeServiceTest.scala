package com.zs.prime.grpc


import akka.actor.testkit.typed.scaladsl.ActorTestKit
import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.grpc.GrpcClientSettings
import com.typesafe.config.ConfigFactory
import com.zs.prime.PrimeServerS
import org.scalatest.BeforeAndAfterAll
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable
import scala.concurrent.duration._
import scala.util.{Failure, Success}

class PrimeServiceTest extends AnyWordSpec
  with BeforeAndAfterAll
  with Matchers
  with ScalaFutures {

  implicit val patience: PatienceConfig = PatienceConfig(scaled(5.seconds), scaled(100.millis))

  // important to enable HTTP/2 in server ActorSystem's config
  val conf = ConfigFactory.parseString("akka.http.server.preview.enable-http2 = on")
    .withFallback(ConfigFactory.defaultApplication())

  val testKit = ActorTestKit(conf)

  val serverSystem: ActorSystem[_] = testKit.system
  val bound = new PrimeServerS(serverSystem.classicSystem).run()

  // make sure server is bound before using client
  bound.futureValue

  implicit val clientSystem: ActorSystem[_] = ActorSystem(Behaviors.empty, "PrimeClient")

  val client =
    PrimeServiceClient(
      GrpcClientSettings.connectToServiceAt("127.0.0.1", 10001).withTls(false)
    )

  override def afterAll: Unit = {
    ActorTestKit.shutdown(clientSystem)
    ActorTestKit.shutdown(serverSystem)
    testKit.shutdownTestKit()
  }

  "PrimeService" should {
    "return list of primes" in {
      def verify(n: Int, expected: Array[Int]) = {
        val request = PrimeRequest.of(n)
        val reply = client.getPrimes(request)
        val queue = mutable.Queue[Int]()
        reply.runForeach(
          r => queue.enqueue(r.prime)
        ).onComplete {
          case Success(_) =>
            assert(queue.toArray.equals(expected))
            println("streaming done")
          case Failure(e) =>
            println(s"Error : $e")
        }(clientSystem.executionContext)
      }

      verify(1, Array(1))
      verify(2, Array(1, 2))
      verify(5, Array(1, 2, 3, 5))
      verify(10, Array(1, 2, 3, 5, 7))
      verify(20, Array(1, 2, 3, 5, 7, 11, 13, 17, 19))
      verify(50, Array(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
    }
  }
}