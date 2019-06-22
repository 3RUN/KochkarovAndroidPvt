package by.itacademy.pvt.dz2

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import by.itacademy.pvt.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dz2.*

class Dz2Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        val image = findViewById<View>(R.id.imageLoaderView)

        findViewById<View>(R.id.loadButton)
            .setOnClickListener {

                showProgressBar()

                // disable our background on each click
                // this one is needed f.e. to hide error background
                // or previously loaded background
                image.background = null

                val editTextView = findViewById<EditText>(R.id.urlEditText)
                val url = editTextView.text.toString()

                Picasso.get()
                    .load(url)
                    .transform(CircleTransform())
                    .into(imageLoaderView, object : Callback {

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
        view.background = getDrawable(R.drawable.error_icon)
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