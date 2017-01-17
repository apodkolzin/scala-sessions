val in = scala.io.Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

val words: List[String] = in.getLines().toList

val mnemonics: Map[Char, String] = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

val charCode: Map[Char, Char] = mnemonics.flatMap(item => (item._2.toCharArray.map(char => (char, item._1))))

def wordCode(word: String): String = {
  word.toUpperCase.toCharArray.map(char => charCode.getOrElse(char, "1").toString).reduceLeft { (acum, value) => acum.concat(value)}
}

wordCode("Scala") == "72252"

val wordsForNum: Map[String, Seq[String]] = words.groupBy(item => wordCode(item))

def encode(number: String): Set[List[String]] = {

  def findVariantsSentence(result: Set[List[String]]): Set[List[String]] = {
    findWordTuple(number, 0) match {
      case (resultString, true) => result ++ Set(findWord(resultString))
      case (resultString, _) => result ++ Set(findWord(resultString)) ++ Set(findWord(findWordTuple(number drop (resultString.size), 0)._1))
      case _ => throw new Exception("UUUUps...")
    }
  }

  def findWordTuple(word: String, wordIndex: Int): (String, Boolean) = {

    findWord(word.take(wordIndex)).size match {
      case 0 => (word.take(wordIndex - 1), false) //Предыдущее было последнее
      case 1 => (word.take(wordIndex), false) // сие последнее
      case _ if wordIndex <= word.length => findWordTuple(word, wordIndex + 1) //ищем далече
      case _ => (word, true) //закончили упражнение
    }
  }

  def findWord(word: String): List[String] = {
    words.groupBy(item => wordCode(item)).filter(item => item._1.startsWith(word)).flatMap(_._2).toList
  }

  findVariantsSentence(Set.empty)
}


def translate(number: String): Set[String] = encode(number) map (_ mkString " ")

println(translate("72252663666427"))

translate("722521663666427").contains("Scala mnemonics")
translate("72252")


val foo: ()=>Int = {()=>1}