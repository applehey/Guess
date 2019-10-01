package com.example.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.guess.data.GameDatabase
import com.example.guess.data.Record
import kotlinx.android.synthetic.main.activity_record.*
import java.util.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER", -1)
        counter.setText(count.toString())
//--
        val bless = listOf(
            getString(R.string.bless_1),
            getString(R.string.bless_2),
            getString(R.string.bless_3),
            getString(R.string.bless_4),
            getString(R.string.bless_5)
        )
        fun bless() : String {
            var ble = Random().nextInt(3)
            var bless = bless.get(ble)
            return bless
        }
        blessText.text = bless()
//--

        //OnClickListener
        save.setOnClickListener { view ->
            val nick = nickname.text.toString()
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER", count)
                .putString("REC_NICKNAME", nick)
                .apply()
            //insert to Room
            Thread(){
                GameDatabase.getInstance(this)?.recordDao()?.insert(Record(nick, count))
            }.start()

            val intent = Intent()
            intent.putExtra("NICK", nick)
            setResult(Activity.RESULT_OK, intent)
            finish()

        }
    }
}
