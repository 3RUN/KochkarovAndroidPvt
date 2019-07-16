package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

interface Dz11EditContract {

    interface View {

        fun showStudent(student: Student)
    }

    interface Presenter {

        fun onViewDestroyed()

        fun loadStudentById(id: UUID)

        fun createStudent(name: String, url: String, age: Int)

        fun editStudent(id: UUID, name: String, url: String, age: Int)
    }
}