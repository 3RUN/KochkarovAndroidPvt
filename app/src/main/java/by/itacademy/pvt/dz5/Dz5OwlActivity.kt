package by.itacademy.pvt.dz5

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import by.itacademy.pvt.R

class Dz5OwlActivity : Activity() {

    private val frameAnimation: AnimationDrawable =
        findViewById<ImageView>(R.id.dz5OwlId).background as AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz5owl)
    }

    override fun onResume() {
        super.onResume()
        frameAnimation.start()
    }

    override fun onPause() {
        super.onPause()
        frameAnimation.stop()
    }
}