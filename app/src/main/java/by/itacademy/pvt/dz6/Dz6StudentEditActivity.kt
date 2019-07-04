package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentProvider.addStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.replaceStudent
import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

class Dz6StudentEditActivity : Activity() {

    companion object {
        const val ID_EXTRA = "ID_TEXT_EXTRA"

        fun getIntent(context: Context, id: UUID): Intent {
            val intent = Intent(
                context,
                Dz6StudentEditActivity::class.java
            )
            intent.putExtra(ID_EXTRA, id.toString())
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6edit)

        val nameText = findViewById<EditText>(R.id.nameEditTextId)
        val ageText = findViewById<EditText>(R.id.ageEditTextId)
        val urlText = findViewById<EditText>(R.id.urlEditTextId)
        val saveButton = findViewById<Button>(R.id.saveButtonId)

        // edit existing student ?
        if (intent.hasExtra(ID_EXTRA)) {

            val idStr = intent.getStringExtra(ID_EXTRA)
            val student: Student = getStudent(UUID.fromString(idStr))

            if (student == null) {
                Toast.makeText(this, "Student not found. Wrong id!", Toast.LENGTH_SHORT).show()
                exitToMainList()
            }

            loadStudentDetails(student, nameText, ageText, urlText)

            saveButton
                .setOnClickListener {
                    // valid input ?
                    // then save all edit
                    if (checkInput(nameText, ageText, urlText)) {
                        editStudentAndExit(
                            student,
                            nameText.text.toString(),
                            urlText.text.toString(),
                            (ageText.text.toString()).toInt()
                        )
                    } else {
                        if (!checkName(nameText)) {
                            castError("Wrong input in name field!")
                        } else if (!checkAge(ageText)) {
                            castError("Wrong input in age field!")
                        } else if (!checkUrl(urlText)) {
                            castError("Wrong input in URL field!")
                        }
                    }
                }
        } else {
            // creating new one!
            saveButton
                .setOnClickListener {
                    // valid input ?
                    // then save this student
                    if (checkInput(nameText, ageText, urlText)) {
                        createStudentAndExit(
                            nameText.text.toString(),
                            urlText.text.toString(),
                            (ageText.text.toString()).toInt()
                        )
                    } else {
                        if (!checkName(nameText)) {
                            castError("Wrong input in name field!")
                        } else if (!checkAge(ageText)) {
                            castError("Wrong input in age field!")
                        } else if (!checkUrl(urlText)) {
                            castError("Wrong input in URL field!")
                        }
                    }
                }
        }
    }

    private fun exitToMainList() {
        val intent = Intent(this, Dz6StudentListActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun createStudentAndExit(name: String, url: String, age: Int) {
        addStudent(name, url, age)
        exitToMainList()
    }

    private fun editStudentAndExit(student: Student, name: String, url: String, age: Int) {
        val newStudent = Student(
            student.id,
            name,
            url,
            age
        )
        replaceStudent(newStudent)
        exitToMainList()
    }

    private fun loadStudentDetails(student: Student, nameView: EditText, ageView: EditText, urlView: EditText) {
        nameView.setText(student.name, TextView.BufferType.EDITABLE)
        ageView.setText(student.age.toString(), TextView.BufferType.EDITABLE)
        urlView.setText(student.url, TextView.BufferType.EDITABLE)
    }

    private fun castError(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }

    private fun checkUrl(urlText: EditText): Boolean =
        urlText.text.toString().isNotEmpty() && Patterns.WEB_URL.matcher(urlText.text.toString()).matches()

    private fun checkName(nameText: EditText): Boolean =
        nameText.text.toString().isNotEmpty() && !nameText.text.toString().isDigitsOnly()

    private fun checkAge(ageText: EditText): Boolean =
        ageText.text.toString().isNotEmpty() && ageText.text.toString().isDigitsOnly() &&
                ageText.text.toString().length <= 3 && ageText.text.toString().toInt() >= 1

    private fun checkInput(nameText: EditText, ageText: EditText, urlText: EditText): Boolean =
        checkUrl(urlText) && checkName(nameText) && checkAge(ageText)
}