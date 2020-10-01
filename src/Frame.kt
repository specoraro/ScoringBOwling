class Frame (first:Int, second:Int) {
    //ogni frame permette massimo due tiri e deve tenere conto di enventuali bonus
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