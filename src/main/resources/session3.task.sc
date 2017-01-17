
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