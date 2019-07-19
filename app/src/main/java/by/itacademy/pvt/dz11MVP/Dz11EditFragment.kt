package by.itacademy.pvt.dz11MVP

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
import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

class Dz11EditFragment : Fragment(), Dz11EditContract.View {

    companion object {
        private const val ID_KEY = "ID_KEY"
        val TAG = Dz11EditFragment::class.java.canonicalName!!

        fun getInstance(id: String? = null): Dz11EditFragment {
            val fragment = Dz11EditFragment()

            if (id != null) {
                val bundle = Bundle()
                bundle.putString(ID_KEY, id)
                fragment.arguments = bundle
            }

            return fragment
        }
    }

    private var clickListener: Listener? = null
    private lateinit var presenter: Dz11EditPresenter
    private var isNewStudent: Boolean = true
    private lateinit var nameTextView: EditText
    private lateinit var ageTextView: EditText
    private lateinit var urlTextView: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dz8edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = Dz11EditPresenter()
        presenter.setView(this)

        nameTextView = view.findViewById<EditText>(R.id.nameEditTextId)
        ageTextView = view.findViewById<EditText>(R.id.ageEditTextId)
        urlTextView = view.findViewById<EditText>(R.id.urlEditTextId)
        saveButton = view.findViewById<Button>(R.id.saveButtonId)

        val studentId = arguments?.getString(ID_KEY, null)

        // edit existing student ?
        if (studentId != null) {
            isNewStudent = false
            presenter.loadStudentById(UUID.fromString(studentId))
        }

        saveButton
            .setOnClickListener {
                if (checkInput(nameTextView, ageTextView, urlTextView)) {
                    if (isNewStudent) {
                        presenter.createStudent(
                            nameTextView.text.toString(),
                            urlTextView.text.toString(),
                            (ageTextView.text.toString()).toInt()
                        )
                    } else {
                        presenter.editStudent(
                            UUID.fromString(studentId),
                            nameTextView.text.toString(),
                            urlTextView.text.toString(),
                            (ageTextView.text.toString()).toInt()
                        )
                    }
                    clickListener?.onSaveClick()
                } else {
                    inputError(nameTextView, ageTextView, urlTextView)
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

    override fun onDestroyView() {
        presenter.onViewDestroyed()
        super.onDestroyView()
    }

    override fun showStudent(student: Student) {
        nameTextView.setText(student.name, TextView.BufferType.EDITABLE)
        ageTextView.setText(student.age.toString(), TextView.BufferType.EDITABLE)
        urlTextView.setText(student.url, TextView.BufferType.EDITABLE)
    }

    private fun inputError(
        nameText: EditText,
        ageText: EditText,
        urlText: EditText
    ) {
        if (!checkUrl(urlText)) {
            castError(getString(R.string.dz6WrongUrl))
        } else if (!checkName(nameText)) {
            castError(getString(R.string.dz6WrongName))
        } else if (!checkAge(ageText)) {
            castError(getString(R.string.dz6WrongAge))
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

    interface Listener {
        fun onSaveClick()
    }
}