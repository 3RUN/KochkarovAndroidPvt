package by.itacademy.pvt.dz11MVP

import android.content.Context
import android.widget.EditText
import by.itacademy.pvt.dz6.entity.Student

interface Dz11ListContract {

    interface View {

        fun showStudentsList(list: List<Student>)
    }

    interface Presenter {

        fun setContext(context: Context)

        fun setView(view: View)

        fun onViewDestroyed()

        fun searchByName(name: String)

        fun loadStudentsList()

        fun initPrefsManager(editTextFilter: EditText)

        fun savePrefsManager(string: String)
    }
}