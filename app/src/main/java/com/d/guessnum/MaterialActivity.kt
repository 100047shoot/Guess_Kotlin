package com.d.guessnum

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    private val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            AlertDialog.Builder(this)
                .setTitle("Replay game")
                .setMessage("Are you sure")
                .setPositiveButton(getString(R.string.ok))
                //匿名類別 下面帶兩個參數 裡面是要做的事情
                { dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    ed_number.setText("")
                }
                .setNeutralButton("Cancel",null)
                .show()
        }

        counter.setText(secretNumber.count.toString())
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
        else if(secretNumber.count < 3){
            message= getString(R.string.excellent_got_it) + n
        }

        counter.setText(secretNumber.count.toString())

//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok),null)
            .show()
    }
}