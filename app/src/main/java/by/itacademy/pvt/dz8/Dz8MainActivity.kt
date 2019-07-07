package by.itacademy.pvt.dz8

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R
import by.itacademy.pvt.dz8.utils.isLandscape

class Dz8MainActivity : FragmentActivity(), Dz8StudentListFragment.Listener,
    Dz8StudentDetailsFragment.Listener, Dz8StudentEditFragment.Listener {

    private val detailsContainerResId: Int
        get() {
            return if (isLandscape(this)) {
                R.id.containerTwoId
            } else {
                R.id.containerOneId
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz8)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(
                R.id.containerOneId,
                Dz8StudentListFragment(),
                Dz8StudentListFragment.TAG
            )
            transaction.commit()
        }
    }

    override fun onStudentClick(id: String) {
    }

    override fun onAddClick() {
    }

    override fun onDeleteClick() {
    }

    override fun onEditClick(id: String) {
    }

    override fun onSaveClick() {
    }
}