package by.itacademy.pvt.dz6

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.entity.Student
import by.itacademy.pvt.utils.ImageLoaderCallback
import by.itacademy.pvt.utils.imageLoaderCircle

class Dz6ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBarId)
    private val imageView = itemView.findViewById<ImageView>(R.id.imageLoaderViewId)
    private val textView = itemView.findViewById<TextView>(R.id.textViewId)

    fun bind(student: Student) {

        val height = textView.resources.getDimension(R.dimen.dz6ItemImageSize).toInt()

        imageLoaderCircle(student.url, imageView, height, height, object : ImageLoaderCallback {

            override fun onError(e: Exception) {
                hideProgressBar()
                setErrorImage()
            }

            override fun onSuccess() {
                hideProgressBar()
            }
        })

        textView.text = student.name
    }

    private fun setErrorImage() {
        imageView.setImageResource(R.drawable.error_icon)
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }
}