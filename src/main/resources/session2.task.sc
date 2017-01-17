def ??? : Nothing = throw new NotImplementedError

/**
 * Реализация множества целах чисел в исключительно функциональном стиле
 */
object FunSets {
  /**
   * Определяем множество как функцию принадлежности целого значения этому множеству
   * Так, например, множество отрицательных целых чисел будет представлено функцией (х: Int) => х < 0
   */
  type Set = Int => Boolean

  /**
   * Возвращает true, если множество s содержит elem
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Возвращает множество состоящее из одного элемента elem
   */
  def singletonSet(elem: Int): Set = ???


  /**
   * Возвражает множество, являющееся объединением множеств s и t
   */
  def union(s: Set, t: Set): Set = ???

  /**
   * Возвращает множество, являющееся пересечением множеств s и t
   */
  def intersect(s: Set, t: Set): Set = ???

  /**
   * Возвращает множество, являющееся разностью множеств s и t
   * Множество из элементов множества s, невключенных в t
   */
  def diff(s: Set, t: Set): Set = ???


  /**
   * Дальнейшие функции невозможно описать без обхода множеств
   * Поэтому определяем границы возможных множеств от -1000 до 1000
   */
  val bound = 1000

  /**
   * Возвращает true, если p - верно для всех элементов множества s
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (???) ???
      else if (???) ???
      else iter(???)
    }
    iter(???)
  }

  /**
   * Возвращает true, если p - верно хотя бы для одного элемента множества s
   */
  def exists(s: Set, p: Int => Boolean): Boolean = ???

  /**
   * Возвращает множество, состоящее из преобразованных элементов множества s функцией f
   */
  def map(s: Set, f: Int => Int): Set = ???

  /**
   * Displays the contents of a set
   */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /**
   * Prints the contents of a set on the console.
   */
  def printSet(s: Set) {
    println(toString(s))
  }
}