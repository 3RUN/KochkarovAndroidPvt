package by.itacademy.pvt.dz8

import android.os.Bundle
import androidx.fragment.app.Fragment
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
            updateRecycleList()
        }
    }

    override fun onStudentClick(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            detailsContainerResId,
            Dz8StudentDetailsFragment.getInstance(id),
            Dz8StudentDetailsFragment.TAG
        )
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onAddClick() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            detailsContainerResId,
            Dz8StudentEditFragment.getInstance(null),
            Dz8StudentEditFragment.TAG
        )
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDeleteClick() {
        val transaction = supportFragmentManager.beginTransaction()

        if (isLandscape(this)) {
            supportFragmentManager.findFragmentByTag(
                Dz8StudentDetailsFragment.TAG
            )?.apply { transaction.remove(this) }
            transaction.replace(detailsContainerResId, Fragment())
        } else {
            supportFragmentManager.popBackStack()
        }
        updateRecycleList()
        transaction.commit()
    }

    override fun onEditClick(id: String) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            detailsContainerResId,
            Dz8StudentEditFragment.getInstance(id),
            Dz8StudentEditFragment.TAG
        )
        transaction.commit()
    }

    override fun onSaveClick() {
        val transaction = supportFragmentManager.beginTransaction()

        if (isLandscape(this)) {
            supportFragmentManager.findFragmentByTag(
                Dz8StudentEditFragment.TAG
            )?.apply { transaction.remove(this) }
        } else {
            supportFragmentManager.popBackStack()
        }
        updateRecycleList()
        transaction.commit()
    }

    private fun updateRecycleList() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.containerOneId,
            Dz8StudentListFragment(),
            Dz8StudentListFragment.TAG
        )
        transaction.commit()
    }
}