package by.itacademy.pvt.dz11MVP

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import by.itacademy.pvt.R
import by.itacademy.pvt.dz8.utils.isLandscape

class Dz11MVPActivity : FragmentActivity(), Dz11ListFragment.Listener,
    Dz11DetailsFragment.Listener, Dz11EditFragment.Listener {

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
            Dz11DetailsFragment.getInstance(id),
            Dz11DetailsFragment.TAG
        )
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onAddClick() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            detailsContainerResId,
            Dz11EditFragment.getInstance(null),
            Dz11EditFragment.TAG
        )
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onDeleteClick() {
        val transaction = supportFragmentManager.beginTransaction()

        if (isLandscape(this)) {
            supportFragmentManager.findFragmentByTag(
                Dz11DetailsFragment.TAG
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
            Dz11EditFragment.getInstance(id),
            Dz11EditFragment.TAG
        )
        transaction.commit()
    }

    override fun onSaveClick() {
        val transaction = supportFragmentManager.beginTransaction()

        if (isLandscape(this)) {
            supportFragmentManager.findFragmentByTag(
                Dz11EditFragment.TAG
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
            Dz11ListFragment(),
            Dz11ListFragment.TAG
        )
        transaction.commit()
    }
}