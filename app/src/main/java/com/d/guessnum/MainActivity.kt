package com.d.guessnum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //R.id.hello_main
        //R.drawable.pig
        //R.string.ok
        //R.color.messageColor
        Log.d("MainActivity", "secret:${secretNumber.secret}")//tag 代表標籤
    }

    fun check(view: View) {  //變數:類別
        val n = ed_number.text.toString().toInt()
        println("number:$n")
        Log.d("MainActivity", "number:$n")//tag 代表標籤
        val diff = secretNumber.validate(n)
        var message = "Yes,you got it"
        if (diff < 0) {
            message= "Bigger"
        }
        else if(diff > 0){
            message= "smaller"
        }

//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle("Message")
            .setMessage(message)
            .setPositiveButton("OK",null)
            .show()
    }
}