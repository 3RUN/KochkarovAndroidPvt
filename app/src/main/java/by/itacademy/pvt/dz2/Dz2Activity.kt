package by.itacademy.pvt.dz2

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Bundle
import android.view.View
import android.widget.EditText
import by.itacademy.pvt.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_dz2.imageLoaderView
import com.squareup.picasso.Transformation

class Dz2Activity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz2)

        findViewById<View>(R.id.loadButton)
            .setOnClickListener {

                showProgressBar()

                // disable our background on each click
                // this one is needed f.e. to hide error background
                // or previously loaded background
                val image = findViewById<View>(R.id.imageLoaderView)
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

    class CircleTransform : Transformation {

        private var x: Int = 0
        private var y: Int = 0

        override fun transform(source: Bitmap): Bitmap {
            val size = Math.min(source.width, source.height)

            x = (source.width - size) / 2
            y = (source.height - size) / 2

            val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
            if (squaredBitmap !== source) source.recycle()
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)

            val canvas = Canvas(bitmap)
            val paint = Paint()
            val shader = BitmapShader(squaredBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            paint.shader = shader
            paint.isAntiAlias = true

            val r = size / 2f
            canvas.drawCircle(r, r, r, paint)

            squaredBitmap.recycle()
            return bitmap
        }

        override fun key() = "circle(x=$x,y=$y)"
    }

    private fun setErrorImage() {
        val view = findViewById<View>(R.id.imageLoaderView)
        view.background = getDrawable(R.drawable.error)
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