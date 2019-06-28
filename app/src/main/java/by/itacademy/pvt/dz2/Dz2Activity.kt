package by.itacademy.pvt.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import by.itacademy.pvt.R
import by.itacademy.pvt.utils.ImageLoaderCallback
import by.itacademy.pvt.utils.imageLoaderCircle
import kotlinx.android.synthetic.main.activity_dz2.*

class Dz2Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        val image = findViewById<View>(R.id.imageLoaderView)
        val editTextView = findViewById<EditText>(R.id.urlEditText)

        findViewById<View>(R.id.loadButton)
            .setOnClickListener {

                showProgressBar()

                // disable our background on each click
                // this one is needed f.e. to hide error background
                // or previously loaded background
                image.background = null

                val url = editTextView.text.toString()

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
    }

    private fun setErrorImage() {
        val view = findViewById<View>(R.id.imageLoaderView)
        view.background = ContextCompat.getDrawable(this, R.drawable.error_icon)
    }

    private fun showProgressBar() {
        val view = findViewById<View>(R.id.progressBar)
        view.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        val view = findViewById<View>(R.id.progressBar)
        view.visibility = View.GONE
    }
}