package com.d.guessnum

import java.util.*

class SecretNumber {
    val secret : Int = Random().nextInt(10) + 1//產生1~10 變數為屬性 存活在這個類別中任何地方
    var count = 0
    fun validate(number: Int) : Int{
        count ++
        return  number-secret
    }
}

fun main(){
    val  secretNumber = SecretNumber()
    println(secretNumber.secret)
    println("${secretNumber.validate(2)}, count : ${secretNumber.count}")
}
