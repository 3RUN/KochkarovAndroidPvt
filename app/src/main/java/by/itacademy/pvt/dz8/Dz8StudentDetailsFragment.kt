package by.itacademy.pvt.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentProvider
import by.itacademy.pvt.dz6.entity.Student
import by.itacademy.pvt.dz6.utils.loadStudentIcon
import java.util.UUID

class Dz8StudentDetailsFragment : Fragment() {

    companion object {
        private const val ID_KEY = "ID_KEY"
        val TAG = Dz8StudentDetailsFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz8StudentDetailsFragment {
            val fragment = Dz8StudentDetailsFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    private var clickListener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dz8details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val studentId = arguments?.getString(ID_KEY, null)
        val student: Student = Dz6StudentProvider.getStudent(UUID.fromString(studentId))

        if (student == null) {
            Toast.makeText(view.context, "Student not found. Wrong id!", Toast.LENGTH_SHORT).show()
            clickListener?.onDeleteClick() // simulate delete button click, to hide this fragment
        }

        loadStudentDetails(view, student)

        val editButton = view.findViewById<View>(R.id.editButtonId)
        val deleteButton = view.findViewById<View>(R.id.deleteButtonId)

        editButton
            .setOnClickListener {
                clickListener?.onEditClick(student.id.toString())
            }

        deleteButton
            .setOnClickListener {
                removeStudent(student)
                clickListener?.onDeleteClick()
            }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Listener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    private fun removeStudent(student: Student) {
        Dz6StudentProvider.removeStudent(student)
    }

    private fun loadStudentDetails(view: View, student: Student) {
        val imageView = view.findViewById<ImageView>(R.id.imageLoaderViewId)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarId)
        val imageSize = resources.getDimension(R.dimen.dz6DetailImageSize).toInt()
        loadStudentIcon(imageSize, student, imageView, progressBar)

        val nameText = view.findViewById<TextView>(R.id.studentNameFieldId)
        nameText.text = student.name

        val ageText = view.findViewById<TextView>(R.id.studentAgeFieldId)
        ageText.text = student.age.toString()
    }

    interface Listener {
        fun onEditClick(id: String)
        fun onDeleteClick()
    }
}