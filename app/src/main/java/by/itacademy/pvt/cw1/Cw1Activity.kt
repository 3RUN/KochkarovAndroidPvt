package by.itacademy.pvt.cw1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.itacademy.pvt.R
import by.itacademy.pvt.dz0.Dz0Activity

class Cw1Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cw1)

        findViewById<View>(R.id.oneButton)
            .setOnClickListener {
                startDz0()
            }

        /*
        * kotlin example
        findViewById<Button>(R.id.oneButton)
            .setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                }
            })
            */
    }

    private fun startDz0() {
        val intent = Intent(this, Dz0Activity::class.java)
        startActivity(intent)

        // перейти назад
        // finish()

        // перейти назад
        // onBackPressed()
    }
}