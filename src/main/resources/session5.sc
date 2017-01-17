//def ??? = throw new Error

//----------------
// 1. Collection Hierarchy
//----------------
// список есть элемент %)
//
/*
                 Iterable
            /       |      \
      Seq          Set     Map
  /    |    \
List Vector Range
*/

Seq(1,2,3)


// Vectors
//
// access to element   - o(n)
// access to vector - log 32 (n)
//
// new in list   - o(0)
// new in vector - log 32 (n)
val v = Vector(1,2,3)
4 +: v :+ 5


// Ranges
1 to 10
1 until 10
1 to 10 by 3


// Sets


 val ten = (0 to 10).toSet

/*
1. Множества не упорядочены
2. Множества не содержат дублей
3. Основной метод множества - contains :
*/
ten contains 5
ten map (_ / 2)
//----------------
// 2. High order function
//----------------
val list = (1 to 10).toList

list map (_ * 2)
list flatMap (1 to _)

list filter (_ % 2 == 0)
list partition (_ % 2 == 0)
list takeWhile (_ < 4)
list dropWhile (_ < 4)
list span (_ % 4 != 0)

list exists (_ % 2 == 0)
list forall (_ % 2 == 0)

list zip (10 to 20)

def isPrime(n: Int): Boolean = ???

def pack(list: List[String]) = ???
pack(List("a", "a", "a", "b", "c", "c", "a"))
List(List("a", "a", "a"), List("b"), List("c", "c"), List("a"))
def encode(list: List[String]) = ???
encode(List("a", "a", "a", "b", "c", "c", "a"))
List(("a", 3), ("b", 1), ("c", 2), ("a", 1))



//----------------
// 3. Reduction
//----------------

// reduce (5.6)
/*

sum(List(x1, ..., xn))     = 0 + x1 + ... + xn
product(List(x1, ..., xn)) = 1 * x1 * ... * xn
List(x1, ..., xn) reduceLeft op = (...(x1 op x2) op ... ) op xn

*/

def sum(xs: List[Int]) = (0 :: xs) reduceLeft (_ + _)
def product(xs: List[Int]) = (1 :: xs) reduceLeft (_ * _)

// foldLeft
/*
 (List(x1, ..., xn) foldLeft z)(op) = (...(z op x1) op ... ) op xn
 */
def sum2(xs: List[Int])     = (xs foldLeft 0) (_ + _)
def product2(xs: List[Int]) = (xs foldLeft 1) (_ * _)
def reverse[T](xs: List[T]): List[T] = (xs foldLeft ???)(???)
def mapFun[T, U](xs: List[T], f: T => U): List[U] = (xs foldRight List[U]())( ??? )
def lengthFun[T](xs: List[T]): Int = (xs foldRight 0)( ??? )

//----------------
// 4. For expressions
//----------------
//(6.2, 6.4)

for {
  i <- 1 until 10
  j <- 1 until 10
  if isPrime(i + j)
} yield (i, j)


case class Book(title: String, authors: List[String])

val books: List[Book] = List(
  Book(title = "Structure and Interpretation of Computer Programs", authors = List("Abelson, Harald", "Sussman, Gerald J.")),
  Book(title = "Introduction to Functional Programming", authors = List("Bird, Richard", "Wadler, Phil")),
  Book(title = "Effective Java", authors = List("Bloch, Joshua")),
  Book(title = "Java Puzzlers", authors = List("Bloch, Joshua", "Gafter, Neal")),
  Book(title = "Programming in Scala", authors = List("Odersky, Martin", "Spoon, Lex", "Venners, Bill")))

for {
  b1 <- books
  b2 <- books
  if b1 != b2
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1
