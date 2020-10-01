/**
 * la funzione main avvia il gioco creando una nuova istanza di <Game> e lo avvia chiamandone il metodo opportuno.
 * Infine stampa il risultato del calcolo secondo le regole del bowling
 */
fun main(){
    val g= Game()
    g.startGame()
    var score=g.score()
    println("***********************************\n" +
            "     the Final Score is $score\n"+
            "***********************************\n")
}