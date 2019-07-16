package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

interface Dz11DetailsContract {

    interface View {

        fun showStudent(student: Student)
    }

    interface Presenter {

        fun onViewDestroyed()

        fun loadStudentById(id: UUID)

        fun deleteStudent(id: UUID)
    }
}