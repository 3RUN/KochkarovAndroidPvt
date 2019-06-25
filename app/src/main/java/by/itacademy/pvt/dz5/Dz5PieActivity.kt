package by.itacademy.pvt.dz5

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R

class Dz5PieActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5pie)

        val myView = findViewById<Dz5PieView>(R.id.dz5PieId)
        myView.pieData = arrayOf(13.0, 20.0, 7.0, 5.0, 14.0)
    }
}