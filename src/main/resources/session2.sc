def ??? = throw new Error

// high order function

//sum between x and y
def sumInts(x: Int, y: Int): Int =
  if (x > y) 0
  else x + sumInts(x + 1, y)
def square(x:Int) = x * x
def sumSquare(x: Int, y: Int): Int =
  if (x > y) 0
  else square(x) + sumSquare(x + 1, y)

// sumCubes
// sumFact
// S f(x)

// вызов с использовании именованных и анонимных фукнций
// задание: написание функции произведения, что общего с суммой

// каррирование

val s: (Int, Int) => Int = sumInts
def sum(f: Int => Int)(x: Int, y:Int): Int =
  if (x > y) 0
  else f(x) + sum(f)(x + 1, y)
def sumSquare2 = sum(square)
// вопрос: тип функции
val s2: (Int => Int) => ((Int, Int) => Int) = sum    // functional types associate to the right

def product(f: Int => Int)(x: Int, y:Int) = ???
def fact = ??? // use product
def mapReduce = ???
//def  sum = mapReduce((x,y) => x + y)
//def  sum = mapReduce((x,y) => x + y)



//неподвижная точка https://ru.wikipedia.org/wiki/%D0%9D%D0%B5%D0%BF%D0%BE%D0%B4%D0%B2%D0%B8%D0%B6%D0%BD%D0%B0%D1%8F_%D1%82%D0%BE%D1%87%D0%BA%D0%B0
// задача реализация апроксимации
// использование для sqrt y*y = x => y (y + x/y)/2



// рациональные числа (дроби)

{
  class Rational(x: Int, y: Int){


    def numer = x
    def denom = y

    def add(r: Rational): Rational = ???
    def mul(r: Rational): Rational = ???

    def neg: Rational = ???
    def sub(r: Rational) = ???



    override def toString: String = numer + "/" + denom
  }

  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)
  x.add(y).mul(z)
  x.add(y)
  // упражнение: сократить (reduce) дробь

  x.add(y)

  // val g =  gcd(x, y)
  // def numer = ???
  // def denom = ???
  //def less(that: Rational) = ??? numer * that.denom < denom * that.numer
  //def max(that: Rational) =  ??? if (less(that)) that else this
  //x.less(y)
  //x.max(y)

  val strange = new Rational(1, 0)
  strange.add(strange)

  //require(y != 0, "Делитель не может быть равен 0")
  //перенос сокращения дроби в toString

  // инфиксная нотация
  x add y sub z
  x less y
  y max z
}

{
  // идентификаторы в скала
  // Буквенно-цифровые
  // Символические
  // _
  // Буквенно-цифровые_символические
  // x1  ++   +%:?=  v2_++ v3_=

  class Rational(x: Int, y: Int){


    def numer = x
    def denom = y

    def + (r: Rational): Rational = ???
    def * (r: Rational): Rational = ???

    def unary_-: Rational = ???
    def - (that: Rational) = this + -that



    override def toString: String = numer + "/" + denom
  }

  val x = new Rational(1, 3)
  val y = new Rational(5, 7)
  val z = new Rational(3, 2)

  x - y - z
  (x * x) + (y * y)


  /*
 Приоритеты инфиксных операторов по возрастанию согласно первому символу
(all letters)
|
^
&
< >
= !
:
+ -
* / %
(all other special characters)
   */
  // Упражнение:
  //a + b ^? c ?^ d less a ==> b | c
}