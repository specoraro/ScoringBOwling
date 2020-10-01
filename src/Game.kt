
class Game() {
    val SIZE=10
    var frames: Array<Frame?> = arrayOfNulls<Frame>(SIZE) // array per contenere i 10 frames per ciascun gioco
    var frameIndex: Int = 0

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
        if(frameIndex<SIZE) {
            frames[frameIndex] = frame
            frameIndex++
        }

    }
    fun roll( pins: Int): Int {
        var currentRoll=pins
        return currentRoll
    }
    fun frameScore(index:Int){
        val frame=frames[index]!!
        val rollScore= frame.firstRoll+frame.secondRoll //punteggio derivato dai soli tiri

        //controlli per assegnamento dei punti extra
        if(rollScore!=10) frame.frameScore= rollScore
        if(frame.isSpare()){
            if (index==SIZE-1){
                println("Extra roll for Spare!! --> ")
                val extra= readLine()!!.toInt()
                frame.frameScore=10+extra
            }else {
                frame.frameScore = 10 + frames[index + 1]!!.firstRoll
            }
        }
        if(frame.isStrike()) {
            if (index == SIZE-1) {
                println("Extra roll1 for Strike!! --> ")
                val extra1 = readLine()!!.toInt()
                println("Extra roll2 for Strike!! --> ")
                val extra2 = readLine()!!.toInt()
                frame.frameScore = 10 + extra1 + extra2
            }
            else if (frames[index + 1]!!.isStrike()) {
                    frame.frameScore = 10 + 10 + frames[index + 2]!!.firstRoll
                } else {
                    frame.frameScore = 10 + frames[index + 1]!!.firstRoll + frames[index + 1]!!.secondRoll
                }

        }

    }
}