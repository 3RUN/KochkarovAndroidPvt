package by.itacademy.pvt.dz3

import android.app.Activity
import android.os.Bundle
import android.view.View
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.ImageLoaderCallback
import by.itacademy.pvt.utils.imageLoaderCircle
import kotlinx.android.synthetic.main.activity_dz3.*

class Dz3Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz3)

        val url = getString(R.string.imageURL)

        imageLoaderCircle(url, imageLoaderView, object : ImageLoaderCallback {

            override fun onError(e: Exception) {
                hideProgressBar()
                setErrorImage()
            }

            override fun onSuccess() {
                hideProgressBar()
            }
        })
    }

    private fun setErrorImage() {
        val view = findViewById<View>(R.id.imageLoaderView)
        view.background = getDrawable(R.drawable.error_icon)
    }

    private fun hideProgressBar() {
        val view = findViewById<View>(R.id.progressBar)
        view.visibility = View.GONE
    }
}