package by.itacademy.pvt.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.pvt.R
import by.itacademy.pvt.dz0.Dz0Activity
import by.itacademy.pvt.dz1.Dz1Activity
import by.itacademy.pvt.dz2.Dz2Activity
import by.itacademy.pvt.dz2.Dz2LoginActivity
import by.itacademy.pvt.dz3.Dz3Activity

class MenuActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        findViewById<View>(R.id.dzButtonId)
            .setOnClickListener {
                onClick(findViewById(R.id.dzButtonId))
            }

        findViewById<View>(R.id.dz1ButtonId)
            .setOnClickListener {
                onClick(findViewById(R.id.dz1ButtonId))
            }

        findViewById<View>(R.id.dz2ButtonId)
            .setOnClickListener {
                onClick(findViewById(R.id.dz2ButtonId))
            }

        findViewById<View>(R.id.dz2LoginButtonId)
            .setOnClickListener {
                onClick(findViewById(R.id.dz2LoginButtonId))
            }

        findViewById<View>(R.id.dz3ButtonId)
            .setOnClickListener {
                onClick(findViewById(R.id.dz3ButtonId))
            }
    }

    override fun onClick(p0: View?) {
        val id = p0!!.id
        when (id) {
            R.id.dzButtonId -> {
                val intent = Intent(this, Dz0Activity::class.java)
                startActivity(intent)
            }
            R.id.dz1ButtonId -> {
                val intent = Intent(this, Dz1Activity::class.java)
                startActivity(intent)
            }
            R.id.dz2ButtonId -> {
                val intent = Intent(this, Dz2Activity::class.java)
                startActivity(intent)
            }
            R.id.dz2LoginButtonId -> {
                val intent = Intent(this, Dz2LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.dz3ButtonId -> {
                val intent = Intent(this, Dz3Activity::class.java)
                startActivity(intent)
            }
        }
    }
}