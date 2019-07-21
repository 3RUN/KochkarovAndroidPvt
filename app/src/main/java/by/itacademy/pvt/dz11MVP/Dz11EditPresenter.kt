package by.itacademy.pvt.dz11MVP

import android.content.Context
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentProvider.addStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.replaceStudent
import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

class Dz11EditPresenter : Dz11EditContract.Presenter {

    private var view: Dz11EditContract.View? = null
    private var context: Context? = null

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun setView(view: Dz11EditContract.View) {
        this.view = view
    }

    override fun onViewDestroyed() {
        this.view = null
        this.context = null
    }

    override fun loadStudentById(id: UUID) {
        val student = getStudent(id)
        view?.showStudent(student)
    }

    override fun onSaveButtonClick(
        id: String?,
        nameTextView: EditText,
        ageTextView: EditText,
        urlTextView: EditText
    ) {
        if (checkInput(nameTextView, ageTextView, urlTextView)) {
            if (view?.isNewStudent()!!) {
                createStudent(
                    nameTextView.text.toString(),
                    urlTextView.text.toString(),
                    (ageTextView.text.toString()).toInt()
                )
            } else {
                editStudent(
                    UUID.fromString(id),
                    nameTextView.text.toString(),
                    urlTextView.text.toString(),
                    (ageTextView.text.toString()).toInt()
                )
            }
            view?.onSaveButtonSuccess()
        } else {
            inputError(nameTextView, ageTextView, urlTextView)
        }
    }

    override fun createStudent(name: String, url: String, age: Int) {
        addStudent(name, url, age)
    }

    override fun editStudent(id: UUID, name: String, url: String, age: Int) {
        val newStudent = Student(id, name, url, age)
        replaceStudent(newStudent)
    }

    private fun inputError(
        nameText: EditText,
        ageText: EditText,
        urlText: EditText
    ) {
        if (!checkUrl(urlText)) {
            castError(context?.getString(R.string.dz6WrongUrl)!!)
        } else if (!checkName(nameText)) {
            castError(context?.getString(R.string.dz6WrongName)!!)
        } else if (!checkAge(ageText)) {
            castError(context?.getString(R.string.dz6WrongAge)!!)
        }
    }

    private fun castError(str: String) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
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