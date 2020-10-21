package com.d.guessnum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val secretNumber = SecretNumber()
//    val TAG = "MainActivity"
    val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //R.id.hello_main
        //R.drawable.pig
        //R.string.ok
        //R.color.messageColor
        Log.d(TAG, "secret:${secretNumber.secret}")//tag 代表標籤
    }

    fun check(view: View) {  //變數:類別
        val n = ed_number.text.toString().toInt()
        println("number:$n")
        Log.d(TAG, "number:$n")//tag 代表標籤
        val diff = secretNumber.validate(n)

//        resources.getString(R.string.yes_you_got_it)
        var message = getString(R.string.yes_you_got_it)
        if (diff < 0) {
            message= getString(R.string.bigger)
        }
        else if(diff > 0){
            message= getString(R.string.smaller)
        }

//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok),null)
            .show()
    }
}