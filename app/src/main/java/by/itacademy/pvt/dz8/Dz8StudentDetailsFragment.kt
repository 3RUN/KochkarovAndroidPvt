package by.itacademy.pvt.dz8

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.itacademy.pvt.R

class Dz8StudentDetailsFragment : Fragment() {

    companion object {
        private const val ID_KEY = "ID_KEY"
        val TAG = Dz8StudentDetailsFragment::class.java.canonicalName!!

        fun getInstance(id: String): Dz8StudentDetailsFragment {
            val fragment = Dz8StudentDetailsFragment()

            val bundle = Bundle()
            bundle.putString(ID_KEY, id)
            fragment.arguments = bundle

            return fragment
        }
    }

    private var clickListener: Listener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dz8details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Listener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    interface Listener {
        fun onEditClick(id: String)
        fun onDeleteClick()
    }
}