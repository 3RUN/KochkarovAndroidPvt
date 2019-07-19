package by.itacademy.pvt.dz11MVP

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.entity.Student
import by.itacademy.pvt.dz6.utils.loadStudentIcon
import java.util.UUID

class Dz11DetailsFragment : Fragment(), Dz11DetailsContract.View {

    companion object {
        private const val ID_KEY = "ID_KEY"
        val TAG = Dz11DetailsFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz11DetailsFragment {
            val fragment = Dz11DetailsFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    private var clickListener: Listener? = null
    private lateinit var presenter: Dz11DetailsPresenter
    private lateinit var imageView: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var nameTextView: TextView
    private lateinit var ageTextView: TextView
    private lateinit var editButton: View
    private lateinit var deleteButton: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dz8details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        imageView = view.findViewById<ImageView>(R.id.imageLoaderViewId)
        progressBar = view.findViewById<ProgressBar>(R.id.progressBarId)
        nameTextView = view.findViewById<TextView>(R.id.studentNameFieldId)
        ageTextView = view.findViewById<TextView>(R.id.studentAgeFieldId)
        editButton = view.findViewById<View>(R.id.editButtonId)
        deleteButton = view.findViewById<View>(R.id.deleteButtonId)

        val studentId = arguments?.getString(ID_KEY, null)

        presenter = Dz11DetailsPresenter(UUID.fromString(studentId))
        presenter.setView(this)
        presenter.loadStudentById()

        editButton
            .setOnClickListener {
                clickListener?.onEditClick(studentId!!)
            }

        deleteButton
            .setOnClickListener {
                presenter.deleteStudent()
                clickListener?.onDeleteClick()
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

    override fun showStudent(student: Student) {
        val imageSize = resources.getDimension(R.dimen.dz6DetailImageSize).toInt()
        loadStudentIcon(imageSize, student, imageView, progressBar)

        nameTextView.text = student.name
        ageTextView.text = student.age.toString()
    }

    interface Listener {
        fun onEditClick(id: String)
        fun onDeleteClick()
    }
}