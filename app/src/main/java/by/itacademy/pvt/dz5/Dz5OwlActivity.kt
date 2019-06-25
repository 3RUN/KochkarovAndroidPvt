package by.itacademy.pvt.dz5

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.R

class Dz5OwlActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5owl)

        val frameAnimation: AnimationDrawable = findViewById<ImageView>(R.id.dz5OwlId).background as AnimationDrawable
        frameAnimation.start()
    }
}