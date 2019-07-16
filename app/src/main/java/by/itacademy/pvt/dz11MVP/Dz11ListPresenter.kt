package by.itacademy.pvt.dz11MVP

import by.itacademy.pvt.dz6.Dz6StudentProvider.filter
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudentAsList

class Dz11ListPresenter(view: Dz11ListContract.View) : Dz11ListContract.Presenter {

    private var view: Dz11ListContract.View? = view

    override fun onViewDestroyed() {
        this.view = null
    }

    override fun searchByName(name: String) {
        view?.showSearchResults(filter(name, getStudentAsList()))
    }

    override fun loadStudentsList() {
        view?.showStudentsList(getStudentAsList())
    }
}