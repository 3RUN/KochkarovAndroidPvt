package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.entity.Student

interface Dz11ListContract {

    interface View {

        fun showSearchResults(list: List<Student>)

        fun showStudentsList(list: List<Student>)
    }

    interface Presenter {

        fun onViewDestroyed()

        fun searchByName(name: String)

        fun loadStudentsList()
    }
}