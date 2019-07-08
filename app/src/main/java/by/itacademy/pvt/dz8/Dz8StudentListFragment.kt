package by.itacademy.pvt.dz8

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6ListAdapter
import by.itacademy.pvt.dz6.Dz6StudentProvider
import by.itacademy.pvt.dz6.entity.Student

class Dz8StudentListFragment : Fragment(), Dz6ListAdapter.ClickListener {

    companion object {
        val TAG = Dz8StudentListFragment::class.java.canonicalName!!
    }

    private var clickListener: Listener? = null
    private lateinit var prefsManager: Dz8PrefManager
    private lateinit var editTextFilter: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        prefsManager = Dz8PrefManager(view.context)

        val addStudentButton = view.findViewById<View>(R.id.addButtonId)

        val recycleView = view.findViewById<RecyclerView>(R.id.recycleView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(context)

        Dz6StudentProvider.initStudents()
        val studentsFull = Dz6StudentProvider.getStudentAsList()
        var studentsFiltered = studentsFull.toMutableList()

        if (recycleView.adapter == null) {
            recycleView.adapter = Dz6ListAdapter(studentsFiltered, this)
        }

        editTextFilter = view.findViewById<EditText>(R.id.filterEditId)
        editTextFilter.setText(prefsManager.getUserText())
        if (editTextFilter.text.isNotEmpty()) {
            searchFieldUpdate(recycleView, editTextFilter.text.toString(), studentsFull, studentsFiltered)
        }

        addStudentButton
            .setOnClickListener {
                clickListener?.onAddClick()
            }

        editTextFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchFieldUpdate(recycleView, s.toString(), studentsFull, studentsFiltered)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    override fun onStop() {
        super.onStop()
        prefsManager.saveUserText(editTextFilter.text.toString())
    }

    override fun onStudentClick(student: Student) {
        clickListener?.onStudentClick(student.id.toString())
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

    fun searchFieldUpdate(
        recycleView: RecyclerView,
        s: String,
        studentsFull: MutableList<Student>,
        studentsFiltered: MutableList<Student>
    ) {
        studentsFiltered.clear()
        if (editTextFilter.text.toString().toLowerCase().trim().isNotEmpty()) {
            studentsFiltered.addAll(Dz6StudentProvider.filter(s.toLowerCase(), studentsFull))
        } else {
            studentsFiltered.addAll(studentsFull)
        }
        recycleView.adapter?.notifyDataSetChanged()
    }

    interface Listener {
        fun onStudentClick(id: String)
        fun onAddClick()
    }
}