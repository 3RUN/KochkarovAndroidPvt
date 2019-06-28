package by.itacademy.pvt.utils

import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

interface ImageLoaderCallback : Callback {
    override fun onSuccess()
    override fun onError(e: Exception)

    class imageLoaderEmptyCallback : ImageLoaderCallback {

        override fun onSuccess() {}
        override fun onError(e: Exception) {}
    }
}

fun imageLoader(url: String, imageView: ImageView, callback: ImageLoaderCallback) {
    Picasso.get()
        .load(url)
        .into(imageView, callback)
}

fun imageLoaderCircle(url: String, imageView: ImageView, callback: ImageLoaderCallback) {
    Picasso.get()
        .load(url)
        .transform(CircleTransform())
        .into(imageView, callback)
}