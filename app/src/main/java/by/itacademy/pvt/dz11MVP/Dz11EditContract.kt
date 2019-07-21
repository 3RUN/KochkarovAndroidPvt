package by.itacademy.pvt.dz11MVP

import android.content.Context
import android.widget.EditText
import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

interface Dz11EditContract {

    interface View {

        fun isNewStudent(): Boolean

        fun showStudent(student: Student)

        fun onSaveButtonSuccess()
    }

    interface Presenter {

        fun setContext(context: Context)

        fun setView(view: View)

        fun onViewDestroyed()

        fun loadStudentById(id: UUID)

        fun onSaveButtonClick(
            id: String?,
            nameTextView: EditText,
            ageTextView: EditText,
            urlTextView: EditText
        )

        fun createStudent(name: String, url: String, age: Int)

        fun editStudent(id: UUID, name: String, url: String, age: Int)
    }
}