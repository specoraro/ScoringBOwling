class Frame (first:Int, second:Int) {
    /**
     * la classe Frame sviluppa gli elementi che determinano i vari passaggi del gioco
     * contiene i metodi isStrike() e isSpare() per determinare eventuali bonus da assegnare
     * secondo le regole del bowling
     */
    var firstRoll= first
    var secondRoll=second
    var frameScore=0



    fun isStrike():Boolean{
        return firstRoll==10
    }
    fun isSpare():Boolean{
        if(!this.isStrike()){
            return 10-firstRoll==secondRoll
        }
        return false
    }


}