// ---------------
// implicits
// ---------------

import Ordered._

def msort[T: Ordering](xs: List[T]): List[T] = {
  val n = xs.length / 2
  if (n == 0 ) xs
  else {
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, ys) => ys
      case (xs, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (x < y) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }

    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}
val nums = List(-5, 6, 3, 2, 7)
val fruits = List("apple", "pear", "orange", "pineapple")
msort(nums)
msort(fruits)

Ordering.Int

/*
  1. (lt: (T, T) => Boolean)
  2. (ord: Ordering)
  3. (implicit ord: Ordering)
  4. T: Ordering

*/

/*
  Компилятор ищет неявные определения:
  - имеющее ключевое слово implicit
  - имеещее совместимый тип с T
  - и доступное в точке вызова функции или определеное в объекте компаньоне
 */

// ---------------
// Lazy val
// ---------------

lazy val x = expr

def expr = {
  val x = { print("x"); 1 }
  lazy val y = { print("y"); 2 }
  def z = { print("z"); 3 }
  z + y + x + z + y + x
}
expr


// ---------------
// Streams
// ---------------

def isPrime(n: Int): Boolean = (2 to n - 1 ) forall ( n % _ != 0 )
/*
 k-тое простое число больше n
  */
((1000 to 10000) filter isPrime)(1)


/*
 Stream - список, хвост `tail` которого вычисляется на момент обращения к нему, а во время создания
 */

/*
def cons[T](hd: T, tl: => Stream[T]) = new Stream[T] {
  def head = hd
  lazy val tail = tl
}
*/

val stream1: Stream[Int] = (1 to 100).toStream
val stream2: Stream[Int] = (101 to 200).toStream

0 #:: stream1 #::: stream2

((1000 to 10000).toStream filter isPrime)(1)

def from(n: Int): Stream[Int] = n #:: from(n+1)
val nats = from(0)
/*
  множество чисел, кратных 4м
 */
nats map (_ * 4)
nats filter (_ % 4 == 0)

/*
  Решето Эратосфена - алгоритм нахождения целых чисел
  https://ru.wikipedia.org/wiki/Решето_Эратосфена
  Алгоритм:
    - Начинаем вычислять простые числа с первого простого числа - 2
    - Исключаем все числа кратные 2.
    - Следующее число - 3, - простое
    - Исключаем все числа кратные 3
    - Повторяем: на каждом шаге берем первое число в списке, полученном на прошлом шаге, - оно простое,
                 и исключаем все числа кратные ему
*/
def sieve(s: Stream[Int]): Stream[Int] = s.head #:: sieve(s.tail filter (_ % s.head != 0))
val primes = sieve(from(2))
(primes take 10).toList