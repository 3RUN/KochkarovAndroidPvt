package by.itacademy.pvt.dz6.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.entity.Student
import by.itacademy.pvt.utils.ImageLoaderCallback
import by.itacademy.pvt.utils.imageLoaderCircle

fun loadStudentIcon(imageSize: Int, student: Student, imageView: ImageView, progressBar: ProgressBar) {

    imageLoaderCircle(student.url, imageView, imageSize, imageSize, object : ImageLoaderCallback {
        override fun onError(e: Exception) {
            hideStudentProgressBar(progressBar)
            setStudentErrorImage(imageView, imageSize)
        }

        override fun onSuccess() {
            hideStudentProgressBar(progressBar)
        }
    })
}

private fun setStudentErrorImage(imageView: ImageView, imageSize: Int) {
    imageView.layoutParams.width = imageSize
    imageView.layoutParams.height = imageSize
    imageView.setImageResource(R.drawable.error_icon)
}

private fun hideStudentProgressBar(progressBar: ProgressBar) {
    progressBar.visibility = View.GONE
}