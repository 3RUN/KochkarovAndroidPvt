package by.itacademy.pvt.dz8

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentProvider
import by.itacademy.pvt.dz6.Dz6StudentProvider.addStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.replaceStudent
import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

class Dz8StudentEditFragment : Fragment() {

    companion object {
        private const val ID_KEY = "ID_KEY"
        val TAG = Dz8StudentEditFragment::class.java.canonicalName!!

        fun getInstance(id: String? = null): Dz8StudentEditFragment {
            val fragment = Dz8StudentEditFragment()

            if (id != null) {
                val bundle = Bundle()
                bundle.putString(ID_KEY, id)
                fragment.arguments = bundle
            }

            return fragment
        }
    }

    private var isNewStudent: Boolean = true
    private lateinit var student: Student
    private var clickListener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dz8edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nameText = view.findViewById<EditText>(R.id.nameEditTextId)
        val ageText = view.findViewById<EditText>(R.id.ageEditTextId)
        val urlText = view.findViewById<EditText>(R.id.urlEditTextId)
        val saveButton = view.findViewById<Button>(R.id.saveButtonId)
        val studentId = arguments?.getString(ID_KEY, null)

        // edit existing student ?
        if (studentId != null) {
            isNewStudent = false
            student = Dz6StudentProvider.getStudent(UUID.fromString(studentId))
            loadStudentDetails(student, nameText, ageText, urlText)
        }

        saveButton
            .setOnClickListener {
                if (checkInput(nameText, ageText, urlText)) {
                    if (isNewStudent) {
                        createStudent(
                            nameText.text.toString(),
                            urlText.text.toString(),
                            (ageText.text.toString()).toInt()
                        )
                    } else {
                        editStudent(
                            student,
                            nameText.text.toString(),
                            urlText.text.toString(),
                            (ageText.text.toString()).toInt()
                        )
                    }
                    clickListener?.onSaveClick()
                } else {
                    inputError(nameText, ageText, urlText)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    private fun createStudent(name: String, url: String, age: Int) {
        addStudent(name, url, age)
    }

    private fun editStudent(student: Student, name: String, url: String, age: Int) {
        val newStudent = Student(
            student.id,
            name,
            url,
            age
        )
        replaceStudent(newStudent)
    }

    private fun inputError(
        nameText: EditText,
        ageText: EditText,
        urlText: EditText
    ) {
        if (!checkName(urlText)) {
            castError(getString(R.string.dz6WrongUrl))
        } else if (!checkAge(nameText)) {
            castError(getString(R.string.dz6WrongName))
        } else if (!checkUrl(ageText)) {
            castError(getString(R.string.dz6WrongAge))
        }
    }

    private fun loadStudentDetails(
        student: Student,
        nameView: EditText,
        ageView: EditText,
        urlView: EditText
    ) {
        nameView.setText(student.name, TextView.BufferType.EDITABLE)
        ageView.setText(student.age.toString(), TextView.BufferType.EDITABLE)
        urlView.setText(student.url, TextView.BufferType.EDITABLE)
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

    interface Listener {
        fun onSaveClick()
    }
}