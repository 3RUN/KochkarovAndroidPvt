package by.itacademy.pvt.menu

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.pvt.R
import by.itacademy.pvt.dz0.Dz0Activity
import by.itacademy.pvt.dz1.Dz1Activity
import by.itacademy.pvt.dz11MVP.Dz11MVPActivity
import by.itacademy.pvt.dz11MVVM.Dz11MVVMActivity
import by.itacademy.pvt.dz2.Dz2Activity
import by.itacademy.pvt.dz2.Dz2LoginActivity
import by.itacademy.pvt.dz3.Dz3Activity
import by.itacademy.pvt.dz4.Dz4Activity
import by.itacademy.pvt.dz5.Dz5OwlActivity
import by.itacademy.pvt.dz5.Dz5PieActivity
import by.itacademy.pvt.dz6.Dz6StudentListActivity
import by.itacademy.pvt.dz8.Dz8MainActivity
import by.itacademy.pvt.dz9.Dz9Activity

class MenuActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val dz0Button = findViewById<View>(R.id.dzButtonId)
        val dz1Button = findViewById<View>(R.id.dz1ButtonId)
        val dz2Button = findViewById<View>(R.id.dz2ButtonId)
        val dz2LoginButton = findViewById<View>(R.id.dz2LoginButtonId)
        val dz3Button = findViewById<View>(R.id.dz3ButtonId)
        val dz4Button = findViewById<View>(R.id.dz4ButtonId)
        val dz5PieButton = findViewById<View>(R.id.dz5PieButtonId)
        val dz5OwlButton = findViewById<View>(R.id.dz5OwlButtonId)
        val dz6Button = findViewById<View>(R.id.dz6ButtonId)
        val dz8Button = findViewById<View>(R.id.dz8ButtonId)
        val dz9Button = findViewById<View>(R.id.dz9ButtonId)
        val dz11MVPButton = findViewById<View>(R.id.dz11MVPButtonId)
        val dz11MVVMButton = findViewById<View>(R.id.dz11MVVMButtonId)

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
        dz4Button
            .setOnClickListener {
                onClick(dz4Button)
            }
        dz5PieButton
            .setOnClickListener {
                onClick(dz5PieButton)
            }
        dz5OwlButton
            .setOnClickListener {
                onClick(dz5OwlButton)
            }
        dz6Button
            .setOnClickListener {
                onClick(dz6Button)
            }
        dz8Button
            .setOnClickListener {
                onClick(dz8Button)
            }
        dz9Button
            .setOnClickListener {
                onClick(dz9Button)
            }
        dz11MVPButton
            .setOnClickListener {
                onClick(dz11MVPButton)
            }
        dz11MVVMButton
            .setOnClickListener {
                onClick(dz11MVVMButton)
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
            R.id.dz4ButtonId -> {
                val intent = Intent(this, Dz4Activity::class.java)
                startActivity(intent)
            }
            R.id.dz5PieButtonId -> {
                val intent = Intent(this, Dz5PieActivity::class.java)
                startActivity(intent)
            }
            R.id.dz5OwlButtonId -> {
                val intent = Intent(this, Dz5OwlActivity::class.java)
                startActivity(intent)
            }
            R.id.dz6ButtonId -> {
                val intent = Intent(this, Dz6StudentListActivity::class.java)
                startActivity(intent)
            }
            R.id.dz8ButtonId -> {
                val intent = Intent(this, Dz8MainActivity::class.java)
                startActivity(intent)
            }
            R.id.dz9ButtonId -> {
                val intent = Intent(this, Dz9Activity::class.java)
                startActivity(intent)
            }
            R.id.dz11MVPButtonId -> {
                val intent = Intent(this, Dz11MVPActivity::class.java)
                startActivity(intent)
            }
            R.id.dz11MVVMButtonId -> {
                val intent = Intent(this, Dz11MVVMActivity::class.java)
                startActivity(intent)
            }
        }
    }
}