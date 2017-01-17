def ??? = throw new UnsupportedOperationException()

/*
 *
 * парадигмы программирования: императивное, функциональное, логическое | ООП
 * Архитектура фон Неймана
 * Имеративное программирование
 * * Мутабельные переменные                           - память
 * * Присвоения                                       - загрузка и хранение инструкций
 * * Управляющие структуры if, loops, break, return   - jumps
 * Проблема маштабируемости: в одном месте концентрируется все структуры данных
 *
 * Функциональное программирование
 * * в узком смысле: программирование без мутабельных переменных, присвоений, циклов, изменения состояний
 *   Lisp, Haskell, xslt, xpath
 * в широком: концентрация на определение функций
 *   Clojure, Scheme, Scala, Smalltalk
 *
 *
*/

87 + 145
def size = 2
size * 10
def square(x: Int) = x*x
def squareFirst(x: Int, y: Int) = square(x)
squareFirst(4, 2 + 3)
/*
 * Вычисление выражений
 *
 * Метод подстановки:
 * * call by value
 *   аргумент функции вычисляется перед вызовом функции
 * * call by name
 *   аргумент функции вычисляется при обращении к нему
 */
def loop: Int = loop
//squareFirst(4 + 3, loop)


def and(x: Boolean, y: Boolean): Boolean = ???
def or(x: Boolean, y: Boolean): Boolean = ???

// and(false, loop) == false || loop
// or (true, loop) == true || loop

def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

gcd(14, 21)

def factorial(n: Int) = ???


// write a tail recursion     вычисление на последней итерации - результат изначального вызова
