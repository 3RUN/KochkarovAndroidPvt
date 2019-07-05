package by.itacademy.pvt.dz6

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.entity.Student
import by.itacademy.pvt.dz6.utils.loadStudentIcon

class Dz6ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBarId)
    private val imageView = itemView.findViewById<ImageView>(R.id.imageLoaderViewId)
    private val textView = itemView.findViewById<TextView>(R.id.textViewId)

    fun bind(student: Student) {
        val imageSize = itemView.resources.getDimension(R.dimen.dz6ItemImageSize).toInt()
        loadStudentIcon(imageSize, student, imageView, progressBar)
        textView.text = student.name
    }
}