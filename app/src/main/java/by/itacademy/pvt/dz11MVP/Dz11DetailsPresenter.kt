package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.Dz6StudentProvider
import by.itacademy.pvt.dz6.Dz6StudentProvider.removeStudent
import java.util.UUID

class Dz11DetailsPresenter(view: Dz11DetailsContract.View) : Dz11DetailsContract.Presenter {

    private var view: Dz11DetailsContract.View? = view

    override fun onViewDestroyed() {
        this.view = null
    }

    override fun loadStudentById(id: UUID) {
        val student = Dz6StudentProvider.getStudent(id)
        view?.showStudent(student)
    }

    override fun deleteStudent(id: UUID) {
        removeStudent(id)
    }
}