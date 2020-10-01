
class Game() {
    val SIZE=10
    var frames: Array<Frame?> = arrayOfNulls<Frame>(SIZE) // array per contenere i 10 frames per ciascun gioco
    var frameIndex: Int = 0
    var flag:Boolean=false

    fun score():Int{
        /**
         * il metodo score, una volta che sono stati giocati tutti i frames
         * attraversa con un ciclo for l'intero array e somma i risultati parziali di ogni frame
         * e lo ritorna
         */
        var score=0
        for(i in frames.indices){
            this.frameScore(i)
            score += frames[i]!!.frameScore
        }


        return score
    }

    fun startGame(){
        /**
         * il metodo startGame è il motore del gioco, interagisce e riceve gli input dall'utente
         */
        println("Do you want to start a new game?\nInsert for each roll in frame the number of pins knocked down")
        while(this.frameIndex<=SIZE-1){
            println("-----------" +
                    "| FRAME ${this.frameIndex+1} |" +
                    "-----------\n"+"first roll? ")
            var f:Int=roll(readLine()!!.toInt())
            var s:Int
            if(f==10) {s=roll(0)}
            else{
                println("second roll? ")
                s=roll(readLine()!!.toInt())
            }
            var frame: Frame=Frame(f,s)
            this.addFrame(frame)

        }

    }
    fun addFrame(frame: Frame){
        /**
         * memorizza il frame creato nel metodo startGame nell'array di frames dedicato al gane
         */
        if(frameIndex<SIZE) {
            frames[frameIndex] = frame
            frameIndex++
        }

    }
    fun roll( pins: Int): Int {
        /**
         * indica quanti birilli vengono buttati giù con un tiro
         */
        var currentRoll=pins
        return currentRoll
    }

    fun frameScore(index:Int){
        /**
         * il metodo frameScore si occupa di valutare lo score di ogni frame, tenendo conto anche di eventuali
         * bonus derivati da strikes e spares e dipendenti da frames successivi
         */
        val frame=frames[index]!! // l'asserzione di non-null è derivata dal fatto che frameScore() è chiamata sicuramente su frame giocati e quindi notNUll
        val rollScore= frame.firstRoll+frame.secondRoll //punteggio derivato dai soli tiri

        //controlli per assegnamento dei punti extra
        if(rollScore!=10) frame.frameScore= rollScore//non sono stati eseguiti nè strikes nè spares
        if (frame.isSpare()) {
            if (index != SIZE-1) {
                frame.frameScore = 10 + frames[index + 1]!!.firstRoll
            } else { // condizione per il decimo frame
                println("Extra roll for Spare!! --> ")
                val extra= readLine()!!.toInt()
                frame.frameScore=10+extra
            }
        }
        if(frame.isStrike()) {
            if (index < SIZE-2) {
                if (frames[index + 1]!!.isStrike()) {
                    frame.frameScore = 10 + 10 + frames[index + 2]!!.firstRoll
                } else {
                    frame.frameScore = 10 + frames[index + 1]!!.firstRoll + frames[index + 1]!!.secondRoll
                }
            } else if(index==SIZE-2){//valutazione per nel caso il penultimo frame sia uno strike
                if (frames[index + 1]!!.isStrike()) {
                    println("SONO UNO STRIKE")
                    flag=true
                    println("$flag")
                } else {
                    frame.frameScore = 10 + frames[index + 1]!!.firstRoll + frames[index + 1]!!.secondRoll
                }


            }
            else {// condizione per il decimo frame
                println("Extra roll1 for Strike!! --> ")
                val extra1 = readLine()!!.toInt()
                println("Extra roll2 for Strike!! --> ")
                val extra2 = readLine()!!.toInt()
                if(!flag) {
                    frame.frameScore = 10 + extra1 + extra2
                }
                else if(flag){
                    frame.frameScore = 20+10 + (2*extra1) + extra2//il 20 e il fattore moltiplicativo 2 sono per tener conto dello strike al penultimo frame
                    println("$extra1 efeefefefe")
                }
            }

        }

    }
}