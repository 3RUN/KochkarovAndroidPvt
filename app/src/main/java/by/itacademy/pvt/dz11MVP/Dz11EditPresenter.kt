package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.Dz6StudentProvider.addStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudent
import by.itacademy.pvt.dz6.Dz6StudentProvider.replaceStudent
import by.itacademy.pvt.dz6.entity.Student
import java.util.UUID

class Dz11EditPresenter(view: Dz11EditContract.View) : Dz11EditContract.Presenter {

    private var view: Dz11EditContract.View? = view

    override fun onViewDestroyed() {
        this.view = null
    }

    override fun loadStudentById(id: UUID) {
        val student = getStudent(id)
        view?.showStudent(student)
    }

    override fun createStudent(name: String, url: String, age: Int) {
        addStudent(name, url, age)
    }

    override fun editStudent(id: UUID, name: String, url: String, age: Int) {
        val newStudent = Student(id, name, url, age)
        replaceStudent(newStudent)
    }
}