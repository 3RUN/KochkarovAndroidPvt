package by.itacademy.pvt.dz5

import android.app.Activity
import android.os.Bundle
import by.itacademy.pvt.R

class Dz5PieActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5pie)

        val myView = findViewById<Dz5PieView>(R.id.dz5PieId)
        myView.pieData = arrayOf(13.3, 23.5, 7.8, 13.3, 4.2, 36.5, 13.0, 46.4, 11.3)
    }
}