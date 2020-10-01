fun main(){
    val g= Game(/*stringa o interazione*/)
    g.startGame()
    var score=g.score()
    println("***********************************\n" +
            "     the Final Score is $score\n"+
            "***********************************\n")
}