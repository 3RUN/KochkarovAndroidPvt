package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.entity.Student

interface Dz11DetailsContract {

    interface View {

        fun showStudent(student: Student)
    }

    interface Presenter {

        fun setView(view: View)

        fun onViewDestroyed()

        fun loadStudentById()

        fun deleteStudent()
    }
}