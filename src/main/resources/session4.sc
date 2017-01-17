import java.util.NoSuchElementException
//def ??? = throw new Error

// Иммутабельные структуры данных. Pattern Match


// 1. List
/* Списки состоят их двух конструкций:
 - Nil - пустой список
 - Cons - значение элемента и хвост списка
 */

// IntList
{
  trait IntList
  class Nil extends IntList
  class Cons(val head: Int, val tail: IntList) extends IntList

  // ! нарисовать списки

  {
    class Cons(_head: Int, _tail: IntList) extends IntList {
      val head = _head
      val tail = _tail
    }
  }
}

// типизированный List
{
  trait List[+T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
  }

  object Nil extends List[Nothing] {
    override def isEmpty: Boolean = true
    override def tail: Nothing = throw new NoSuchElementException("Nil.head")
    override def head: Nothing = throw new NoSuchElementException("Nil.tail")
  }

  class Cons[T](val head: T, val tail: List[T]) extends List[T]{
    override def isEmpty: Boolean = false
  }

  def singleton[T](e: T) = new Cons(e, Nil)

  /*
  Type parameters важны для компилятора для проверки соответствия типов, но не используются при выполнении программы
  Такое типизирование называется type erasure
  Аналогично: Java, Haskell
  Сохранение типов в контексте выполнения: C++ и .Net-семейство

  Реализация полиморфизма:
    - subtype
    - generic
 */

}

// Варианты создания списков:

// через конструктор
val fruit = List("apples", "oranges", "pears")
val nums  = List(1, 2, 3, 4)
val diag3 = List(List(1, 0, 0), List(0, 1, 0), List(0, 0, 1))
val empty = List()


// через методы пустого списка:

val nums2 = 1 :: 2 :: 3 :: 4 :: Nil

// правосторонняя ассоциативность оператора
1 + 2 :: Nil

// ? написать выражение через точку




//=========
// 2. IntSet (объектное множество)

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = ???

  override def toString: String = super.toString
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {

  def contains(x: Int): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  def incl(x: Int): IntSet =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  // ! нарисовать процесс создания множества
  // persistent data structures

  def union(other: IntSet): IntSet = ???

  override def toString: String = super.toString
}

val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 5




//=========
// 3. Match

trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
}

/*

Match синтаксис:
 ▶ match предшествует последовательности cases, pat => expr.
 ▶ Каждый case ассоциирует выражение expr с паттерном pat.
 ▶ если селектор не найден возбуждается MatchError.

 Паттернами являются:
▶ конструкторы, например Number, Sum,
▶ переменные, например n, e1, ,e2
▶ wildcard _,
▶ константы, например 1, true.

 */

 List(1, 2, 3) match {
   case x :: y :: _ => x + y
   case x :: _ => x
   case Nil => 0
 }

case class Foo(x: Int, y: Int)

Foo(1,2) match  {
  case x Foo y => x + y
}

def sum(xs: List[Int]): Int = xs match{
  case Nil => 0
  case x :: xs1 => x + sum(xs1)
}

sum(nums)