package by.itacademy.pvt.dz11MVP

import android.content.Context
import android.widget.EditText
import by.itacademy.pvt.dz6.Dz6StudentProvider.filter
import by.itacademy.pvt.dz6.Dz6StudentProvider.getStudentAsList
import by.itacademy.pvt.dz8.Dz8PrefManager

class Dz11ListPresenter : Dz11ListContract.Presenter {

    private var view: Dz11ListContract.View? = null
    private var context: Context? = null
    private lateinit var prefsManager: Dz8PrefManager

    override fun setContext(context: Context) {
        this.context = context
    }

    override fun setView(view: Dz11ListContract.View) {
        this.view = view
    }

    override fun onViewDestroyed() {
        this.view = null
        this.context = null
    }

    override fun searchByName(name: String) {
        view?.showStudentsList(filter(name, getStudentAsList()))
    }

    override fun loadStudentsList() {
        view?.showStudentsList(getStudentAsList())
    }

    override fun initPrefsManager(editTextFilter: EditText) {
        prefsManager = Dz8PrefManager(context!!)
        val prefsString = prefsManager.getUserText()
        if (prefsString.isNotEmpty()) {
            editTextFilter.setText(prefsString)
            searchByName(prefsString)
        }
    }

    override fun savePrefsManager(string: String) {
        prefsManager.saveUserText(string)
    }
}