package by.itacademy.pvt.dz11MVP

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
import by.itacademy.pvt.dz6.Dz6StudentProvider.filter
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudentAsList
import by.itacademy.pvt.dz6.entity.Student
import by.itacademy.pvt.dz8.Dz8PrefManager

class Dz11ListFragment : Fragment(), Dz6ListAdapter.ClickListener, Dz11ListContract.View {

    companion object {
        val TAG = Dz11ListFragment::class.java.canonicalName!!
    }

    private var clickListener: Listener? = null
    private lateinit var prefsManager: Dz8PrefManager
    private val listAdapter = Dz6ListAdapter(emptyList(), this)
    private lateinit var addStudentButton: View
    private lateinit var editTextFilter: EditText
    private lateinit var recycleView: RecyclerView
    private lateinit var presenter: Dz11ListPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz8list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = Dz11ListPresenter(this)
        presenter.loadStudentsList()

        addStudentButton = view.findViewById<View>(R.id.addButtonId)
        editTextFilter = view.findViewById<EditText>(R.id.filterEditId)
        recycleView = view.findViewById<RecyclerView>(R.id.recycleView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(context)

        if (recycleView.adapter == null) {
            recycleView.adapter = listAdapter
        }

        prefsManager = Dz8PrefManager(view.context)
        val prefsString = prefsManager.getUserText()
        if (prefsString.isNotEmpty()) {
            editTextFilter.setText(prefsString)
            presenter.searchByName(prefsString)
        }

        addStudentButton
            .setOnClickListener {
                clickListener?.onAddClick()
            }

        editTextFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                searchFieldUpdate(s.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    override fun onStop() {
        super.onStop()
        prefsManager.saveUserText(editTextFilter.text.toString())
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

    override fun onStudentClick(student: Student) {
        clickListener?.onStudentClick(student.id.toString())
    }

    override fun showSearchResults(list: List<Student>) {
        listAdapter.updateList(list)
    }

    override fun showStudentsList(list: List<Student>) {
        listAdapter.updateList(list)
    }

    fun searchFieldUpdate(name: String) {
        var studentsFiltered: MutableList<Student> = mutableListOf()
        if (name.toLowerCase().trim().isNotEmpty()) {
            studentsFiltered.addAll(filter(name, getStudentAsList()))
        } else {
            studentsFiltered.addAll(getStudentAsList())
        }
        listAdapter.updateList(studentsFiltered)
    }

    interface Listener {
        fun onStudentClick(id: String)
        fun onAddClick()
    }
}