package com.zs.prime.grpc

import scala.jdk.CollectionConverters._
import akka.NotUsed
import akka.stream.Materializer
import akka.stream.scaladsl.Source
import com.zs.prime.math.{Eratosthenes, PrimeIterator}

class PrimeServiceSImpl(implicit mat: Materializer) extends PrimeService {
  override def getPrimes(request: PrimeRequest): Source[PrimeResponse, NotUsed] = {
    println("gRPC server got " + request.n)
    Source.fromIterator(() => new PrimeIterator(request.n).asScala)
      .map(integer => PrimeResponse(integer))
  }
}
