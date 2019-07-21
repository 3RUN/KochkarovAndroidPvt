package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.Dz6StudentProvider
import by.itacademy.pvt.dz6.Dz6StudentProvider.removeStudent
import java.util.UUID

class Dz11DetailsPresenter(val id: UUID) : Dz11DetailsContract.Presenter {

    private var view: Dz11DetailsContract.View? = null

    override fun setView(view: Dz11DetailsContract.View) {
        this.view = view
    }

    override fun onViewDestroyed() {
        this.view = null
    }

    override fun loadStudentById() {
        val student = Dz6StudentProvider.getStudent(id)
        view?.showStudent(student)
    }

    override fun deleteStudent() {
        removeStudent(id)
    }
}