import scala.collection.mutable

//def ??? = throw new Error

/*
  Основные формы полиморфизма:
    - subtype
    - generic
*/

//------------
// Type Bounds
//------------
import Ordered._

abstract class Set[+T] {
  def incl[U >: T :Ordering](x: U): Set[U]
  def contains[U >: T :Ordering](x: U): Boolean
  def union[U >: T](other: Set[U]): Set[U]
}

object Empty extends Set[Nothing] {
  override def incl[U >: Nothing : Ordering](x: U): Set[U] = new NonEmpty(x, Empty, Empty)
  override def contains[U >: Nothing : Ordering](x: U): Boolean = false
  override def union[U >: Nothing](other: Set[U]): Set[U] = other

}

case class NonEmpty[T](elem: T, left: Set[T], right: Set[T]) extends Set[T] {
  override def incl[U >: T :Ordering](x: U): Set[U] =
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this

  override def contains[U >: T:Ordering](x: U): Boolean =
    if (x < elem) left contains x
    else if (x > elem) right contains x
    else true

  override def union[U >: T](other: Set[U]): Set[U] =
    ((left union right) union other) incl elem

}

def comp[T : Ordering](x: T, y: T) = x < y


// Какие есть варианты реализации множеств для других типов?

/*
  Метод assertAllPos
  ▶ получает Set
  ▶ возвращает полученный Set, если все элементы положительные
  ▶ в противном случает throw exception
 */
/*
def assertAllPos[T >: NonEmpty <: Set : Ordering](s: T): T = s match {
  case Empty => s
  case NonEmpty(a, _, _) if a > 0 => s
  case _ => throw new Error
}


val set = new NonEmpty(1, Empty, Empty)
assertAllPos(set).left
assertAllPos(Empty)
*/

//[S >: NonEmpty <: Set]
/*
  - S <: T : S является подтипом типа T
  - S >: T : S является супертипом типа T или T является подтипом типа S .
 */



//------------
// Вариантность: Ковариантность и контравариантность
//------------

// List[NonEmpty] <: List[Set]
// данное отношение - ковариантно

// Array Typing Problem
/*
  NonEmpty [] a = new  NonEmpty []{ new  NonEmpty (1, Empty , Empty )}
  Set [] b = a
  b[0] = Empty
  NonEmpty s = a[0]
 */
// ArrayStateException
// type tag in java

// Принцип подстановки Барбары Лисков
/*
  https://ru.wikipedia.org/wiki/%D0%9F%D1%80%D0%B8%D0%BD%D1%86%D0%B8%D0%BF_%D0%BF%D0%BE%D0%B4%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B8_%D0%91%D0%B0%D1%80%D0%B1%D0%B0%D1%80%D1%8B_%D0%9B%D0%B8%D1%81%D0%BA%D0%BE%D0%B2
  Принцип подстановки Барбары Лисков описывает привило определения подтипов.

  Если A <: B , тогда все что можно сделать с объектом типа B можно сделать с объектом типа  A
 */


mutable.ListBuffer
/*

val a:  Array[NonEmpty] = Array(new NonEmpty(1, Empty, Empty))
val b:  Array[Set] = a
b(0) =  Empty
val s:  NonEmpty = a(0)
*/





// Array[NonEmpty] и  Array[Set] - нековариантны
// мутабельные типы и ковариантность

/*
     A <: B
  C[A] <: C[B] - коваринтны
  C[A] >: C[B] - контравариантны
  иначе        - невариантны
 */

class C1[+A] {  }
class C2[-A] {  }
class C3[A] {  }


// Контравариантность

type A = Set => NonEmpty
type B = NonEmpty => Set

type A1 = NonEmpty => NonEmpty
type B1 = Set   => Set

/* Используя принцип подстановки Лисков
A <: B
B <: A
A и B невариантны.
 */

/*
 Если A2 <: A1 и B1 <: B2 то
A1 => B1 <: A2 => B2
 */

trait Function1[-T, +U] {
  def apply(x: T): U
}

// Проверка вариантности компилятором

/*class Array_[+T] {
  def append(x: T) {}
}*/
/*
  - ковариантные типы должны использоваться только в результатах методов
  - ковариантные типы должны использоваться только в параметрах методов
  см Function1
 */

// def prepend(elem: T): List[T] = new Cons(elem, this)

// ! переделать Set