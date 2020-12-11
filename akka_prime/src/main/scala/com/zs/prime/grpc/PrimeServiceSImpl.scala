package com.zs.prime.grpc

import akka.NotUsed
import akka.stream.Materializer
import akka.stream.scaladsl.Source
import com.zs.prime.math.Eratosthenes

class PrimeServiceSImpl(implicit mat: Materializer) extends PrimeService {
  override def getPrimes(request: PrimeRequest): Source[PrimeResponse, NotUsed] = {
    println("gRPC server got " + request.n);
    val primes = new Eratosthenes().getPrimes(request.n);
    Source(primes).map(integer => PrimeResponse(integer));
  }
}
