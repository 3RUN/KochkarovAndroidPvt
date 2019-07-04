package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.removeStudent
import by.itacademy.pvt.dz6.entity.Student
import by.itacademy.pvt.dz6.utils.loadStudentIcon
import java.util.UUID

class Dz6StudentDetailsActivity : Activity() {

    companion object {
        const val ID_EXTRA = "ID_TEXT_EXTRA"

        fun getIntent(context: Context, id: UUID): Intent {
            val intent = Intent(
                context,
                Dz6StudentDetailsActivity::class.java
            )
            intent.putExtra(ID_EXTRA, id.toString())
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6details)

        val idStr = intent.getStringExtra(ID_EXTRA)
        val student: Student = getStudent(UUID.fromString(idStr))

        if (student == null) {
            Toast.makeText(this, "Student not found. Wrong id!", Toast.LENGTH_SHORT).show()
            exitToMainList()
        }

        loadStudentDetails(student)

        val editButton = findViewById<View>(R.id.editButtonId)
        val deleteButton = findViewById<View>(R.id.deleteButtonId)

        editButton
            .setOnClickListener {
                editStudentAndExit(student)
            }

        deleteButton
            .setOnClickListener {
                removeStudentAndExit(student)
            }
    }

    private fun editStudentAndExit(student: Student) {
        startActivity(Dz6StudentEditActivity.getIntent(this@Dz6StudentDetailsActivity, student.id))
    }

    private fun removeStudentAndExit(student: Student) {
        removeStudent(student)
        exitToMainList()
    }

    private fun exitToMainList() {
        val intent = Intent(this, Dz6StudentListActivity::class.java)
        intent.flags = FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun loadStudentDetails(student: Student) {
        val imageView = findViewById<ImageView>(R.id.imageLoaderViewId)
        val progressBar = findViewById<ProgressBar>(R.id.progressBarId)
        val imageSize = resources.getDimension(R.dimen.dz6DetailImageSize).toInt()
        loadStudentIcon(imageSize, student, imageView, progressBar)

        val nameText = findViewById<TextView>(R.id.studentNameFieldId)
        nameText.text = student.name

        val ageText = findViewById<TextView>(R.id.studentAgeFieldId)
        ageText.text = student.age.toString()

        val urlText = findViewById<TextView>(R.id.studentUrlFieldId)
        urlText.text = student.url
    }
}