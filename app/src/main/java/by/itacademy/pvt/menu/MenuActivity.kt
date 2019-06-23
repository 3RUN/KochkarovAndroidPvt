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

        val dz0Button = findViewById<View>(R.id.dzButtonId)
        val dz1Button = findViewById<View>(R.id.dz1ButtonId)
        val dz2Button = findViewById<View>(R.id.dz2ButtonId)
        val dz2LoginButton = findViewById<View>(R.id.dz2LoginButtonId)
        val dz3Button = findViewById<View>(R.id.dz3ButtonId)

        dz0Button
            .setOnClickListener {
                onClick(dz0Button)
            }

        dz1Button
            .setOnClickListener {
                onClick(dz1Button)
            }

        dz2Button
            .setOnClickListener {
                onClick(dz2Button)
            }

        dz2LoginButton
            .setOnClickListener {
                onClick(dz2LoginButton)
            }

        dz3Button
            .setOnClickListener {
                onClick(dz3Button)
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
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