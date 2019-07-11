package by.itacademy.pvt.dz9

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.CoordParams
import by.itacademy.pvt.dz9.entity.Coordinate
import by.itacademy.pvt.dz9.entity.Poi

class Dz9Fragment : Fragment(), Dz9Adapter.ClickListener {

    private var clickListener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewId)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapterDz9List = Dz9Adapter(emptyList(), this)
        recyclerView.adapter = adapterDz9List

        provideCarRepository().getCarByCoord(
            CoordParams(
                Coordinate(0.0, 0.0),
                Coordinate(0.0, 0.0)
            ),
            { list: List<Poi> ->
                adapterDz9List.updateItems(list)
            },
            {
                Toast.makeText(
                    context,
                    R.string.dz9ErrorMessage,
                    Toast.LENGTH_SHORT
                ).show()
            })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    override fun onCarClick(item: Poi) {
        clickListener?.onCarClick(item.id)
    }

    interface Listener {
        fun onCarClick(id: String)
    }
}