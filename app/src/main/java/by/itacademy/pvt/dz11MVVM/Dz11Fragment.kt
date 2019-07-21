package by.itacademy.pvt.dz11MVVM

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.Dz9Adapter
import by.itacademy.pvt.dz9.entity.Poi

class Dz11Fragment : Fragment() {

    private lateinit var adapterList: Dz9Adapter
    private lateinit var viewModel: Dz11ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapterList = Dz9Adapter(emptyList()) { item: Poi ->
            viewModel.selectCarById(item.id)
        }
        recyclerView.adapter = adapterList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(Dz11ViewModel::class.java)
        viewModel.state.observe(this, Observer {
            when (it) {
                is Dz11State.Ready -> {
                    adapterList.updateItems(viewModel.takeCars())
                }
            }
        })
    }
}