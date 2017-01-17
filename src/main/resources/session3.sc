def ??? = throw new Error

// 1. Function as Object

type F1 = Int => Int

trait Function1[A,Z] {
  def apply(a: A): Z
}

type F2 = (Int,Int) => Int
trait Function2[A,B,Z] {
  def apply(a: A, b: B): Z
}

val f = (a: Int) => a * a
f(7)

val _f = new Function1[Int, Int] {
  override def apply(a: Int): Int = a * a
}
_f.apply(7)
_f(7)




// 2. Иерархия классов в Скала

// Автоматические импорты
// ▶ import scala
// ▶ import java.lang
// ▶ import scala.Predef
// http://www.scala-lang.org/files/archive/api/current

// trait	- признак, особенность, (характерная) черта

trait Planar {
  def height: Int
  def width: Int
  def surface = height * width
}

// Classes, objects and traits can inherit from at most one class but arbitrary many traits.

trait Shape
trait Movable
class Square extends Shape with Planar with Movable {
  override def width: Int = ???
  override def height: Int = ???
}

// Scala class hierarchy
// http://www.scala-lang.org/docu/files/ScalaOverview.pdf

//  Any - базовый тип всех классов
// Методы: ‘==‘, ‘!=‘, ‘equals‘, ‘hashCode, ‘toString‘
// AnyRef - базовый тип всех ссылочных типов, алиас для ‘java.lang.Object‘
// AnyVal - базовый тип примитивных типов

def divide(x: Int, y: Int) =
  if (y == 0 ) ???
  else x / y
//
//var s: String = null
//s = "foo"
// var i: Int = null

if ( true ) 1 else false



// 3. Pure object Boolean
// все объекты, любая операция - вызов метода объекта
// примитивные типы выглядят в скале как классы, но компилятор их преобразует в примитивные java-типы.

// Как создать типы без использования примитивного boolean?



abstract class C {
  def ifThenElse[A](t: => A, e: => A): A
  def == (x: Boolean): Boolean = ifThenElse(x, !x)
  def != (x: Boolean): Boolean = ifThenElse(!x, x)

  def &&(x: => Boolean): Boolean = ifThenElse(x, ложь)
  def ||(x: => Boolean): Boolean = ???
  def unary_! = ???
}


object правда extends Boolean{
  def ifThenElse[A](t: => A, e: => A): A = ???
}

object ложь extends Boolean{
  def ifThenElse[A](t: => A, e: => A): A = ???
}


// h/w Pure object Int (natural)

// Натуральные числа
abstract class Nat{
  // является ли нулем
  def isZero: Boolean
  // возвращает предыдущее натуральное число
  def predecessor: Nat
  // возвращает следущее натуральное число
  def successor: Nat = new Succ(this)
  // возвращает результат сложения с that
  def +(that: Nat): Nat
  // возвращает результат вычитания that
  def -(that: Nat):Nat
}

object Zero extends Nat{
  // является ли нулем
  override def isZero: Boolean = true

  // возвращает предыдущее натуральное число
  override def predecessor: Nat = throw new Error()

  // возвращает результат сложения с that
  override def +(that: Nat): Nat = ???

  // возвращает результат вычитания that
  override def -(that: Nat): Nat = ???
}

class Succ(n: Nat) extends Nat{
  // является ли нулем
  override def isZero: Boolean = false

  // возвращает предыдущее натуральное число
  override def predecessor: Nat = n

  // возвращает результат сложения с that
  override def +(that: Nat): Nat = ???

  // возвращает результат вычитания that
  override def -(that: Nat): Nat = ???
}
