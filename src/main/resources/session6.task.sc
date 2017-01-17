//   Lutz Prechelt: An Empirical Comparison of Seven Programming Languages

val in = scala.io.Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

val words: List[String] = in.getLines().toList

val mnemonics: Map[Char, String] = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

/*
  Получаем на основе карты `mnemonics` отношение Цифра -> Символ
 */
val charCode: Map[Char, Char] = ???

/*
  Кодируем word в набор цифр
 */
def wordCode(word: String): String = ???

wordCode("Scala mnemonics")

/*
  Преобразуем словарь `words` в отношение код -> варианты слов, кодируемые этим кодом
 */
val wordsForNum: Map[String, Seq[String]] = ???

wordsForNum("72252")
wordsForNum("663666427")

/*
  Получаем на основе полученного кода `numbers` набор всех возможных вариантов предложений

  Подсказка: используйте for-expression
 */
def encode(number: String): Set[List[String]] = ???

encode("72252663666427")


def translate(number: String): Set[String] = encode(number) map (_ mkString " ")

translate("72252663666427").contains("Scala mnemonics")
