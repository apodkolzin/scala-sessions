val in = scala.io.Source.fromURL("http://lamp.epfl.ch/files/content/sites/lamp/files/teaching/progfun/linuxwords.txt")

val words: List[String] = in.getLines().toList

val mnemonics: Map[Char, String] = Map('2' -> "ABC", '3' -> "DEF", '4' -> "GHI", '5' -> "JKL", '6' -> "MNO", '7' -> "PQRS", '8' -> "TUV", '9' -> "WXYZ")

val charCode: Map[Char, Char] = (for ((n, s) <- mnemonics; c <- s) yield c -> n) withDefaultValue ' '
val charCode2: Map[Char, Char] = mnemonics flatMap (n => for (c <- n._2) yield c -> n._1) withDefaultValue ' '
val charCode3: Map[Char, Char] = mnemonics flatMap (n => n._2 map (c => c -> n._1)) withDefaultValue ' '

def wordCode(word: String): String = for (c <- word.toUpperCase) yield charCode3(c)
def wordCode2(word: String): String = word.toUpperCase map charCode3

wordCode("scala mnemonics")

wordCode("world")

val wordsForNum: Map[String, Seq[String]] = words groupBy wordCode2 withDefaultValue Seq()

wordsForNum("72252")
wordsForNum("663666427")


def encode(number: String): Set[List[String]] = {
  if (number.isEmpty) Set(List())
  else {
    for {
      split <- 1 to number.length
      word <- wordsForNum(number take split)
      rest <- encode(number drop split)
    } yield word :: rest
  }.toSet
}

def translate(number: String): Set[String] = encode(number) map (_ mkString " ")

encode("663666427").size
encode("72252663666427").size
encode("93557")

translate("72252663666427")

translate("72252663666427").contains("Scala mnemonics")
translate("96753")