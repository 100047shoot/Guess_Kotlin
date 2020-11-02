package com.d.guessnum

import android.content.Intent
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

    private val REQUEST_RECORD: Int = 100
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_material)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
            replay()
        }

        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "onCreate: "+ secretNumber.secret)

        //以下取得sharedPreference
        val count = getSharedPreferences("guess", MODE_PRIVATE)
            .getInt("REC_COUNTER",-1)
        val nick = getSharedPreferences("guess", MODE_PRIVATE)
            .getString("REC_NICKNAME",null)
        Log.d(TAG, "data: " + count + "/" + nick)

    }

    private fun replay() {
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
            .setNeutralButton("Cancel", null)
            .show()
    }

    override fun onStart(){
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onPause(){
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onRestart(){
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
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
            .setPositiveButton(getString(R.string.ok)) { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    //startActivity(intent)
                    startActivityForResult(intent,REQUEST_RECORD)
                }
            }
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_RECORD){
            if(resultCode == RESULT_OK){
                val nickname = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: $nickname")
                replay()
            }
        }
    }
}