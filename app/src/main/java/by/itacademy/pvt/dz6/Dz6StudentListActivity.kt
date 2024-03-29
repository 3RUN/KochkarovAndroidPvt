package by.itacademy.pvt.dz6

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.Dz6StudentProvider.filter
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudentAsList
import by.itacademy.pvt.dz6.entity.Student

class Dz6StudentListActivity : Activity(), Dz6ListAdapter.ClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz6list)

        val editTextFilter = findViewById<EditText>(R.id.filterEditId)
        val addStudentButton = findViewById<View>(R.id.addButtonId)

        val recycleView = findViewById<RecyclerView>(R.id.recycleView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this)

        val studentsFull = getStudentAsList()
        var studentsFiltered = studentsFull.toMutableList()

        if (recycleView.adapter == null) {
            recycleView.adapter = Dz6ListAdapter(studentsFiltered, this)
        }

        addStudentButton
            .setOnClickListener {
                val intent = Intent(this, Dz6StudentEditActivity::class.java)
                startActivity(intent)
            }

        editTextFilter.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                studentsFiltered.clear()
                if (editTextFilter.text.toString().toLowerCase().trim().isNotEmpty()) {
                    studentsFiltered.addAll(filter(s.toString().toLowerCase(), studentsFull))
                } else {
                    studentsFiltered.addAll(studentsFull)
                }
                recycleView.adapter?.notifyDataSetChanged()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
    }

    override fun onStudentClick(student: Student) {
        startActivity(Dz6StudentDetailsActivity.getIntent(this@Dz6StudentListActivity, student.id))
    }
}