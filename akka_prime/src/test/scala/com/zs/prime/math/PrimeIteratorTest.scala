package com.zs.prime.math

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.mutable

class PrimeIteratorTest extends AnyWordSpec with Matchers {

  "PrimeIterator" should {
    "return list of primes" in {
      var iterator = new PrimeIterator(1)
      assert(iterator.hasNext)
      assert(iterator.next === 1)
      assert(iterator.hasNext != true)

      def verify(n: Int, expected: mutable.Queue[Int]): Any = {
        iterator = new PrimeIterator(n)
        val queue = mutable.Queue[Integer]()
        while (iterator.hasNext) {
          queue.enqueue(Int.unbox(iterator.next))
        }
        assert(queue.equals(expected))
      }

      verify(1, mutable.Queue(1))
      verify(2, mutable.Queue(1, 2))
      verify(3, mutable.Queue(1, 2, 3))
      verify(4, mutable.Queue(1, 2, 3))
      verify(5, mutable.Queue(1, 2, 3, 5))
      verify(20, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19))
      verify(47, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
      verify(48, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
      verify(49, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
      verify(50, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
      verify(51, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
      verify(52, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47))
      verify(53, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53))
      verify(54, mutable.Queue(1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53))
    }
  }
}
