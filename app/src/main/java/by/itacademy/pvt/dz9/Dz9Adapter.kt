package by.itacademy.pvt.dz9

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz9.entity.Poi

class Dz9Adapter(private var items: List<Poi>, private val callback: (Poi) -> Unit) :
    RecyclerView.Adapter<Dz9Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dz9, parent, false)

        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            callback(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(items: List<Poi>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onCarClick(item: Poi)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val fleetTypeView = view.findViewById<TextView>(R.id.iconTextViewId)
        private val longitudeTextView = view.findViewById<TextView>(R.id.longitudeInfoId)
        private val latitudeTextView = view.findViewById<TextView>(R.id.latitudeInfoId)

        fun bind(item: Poi) {
            fleetTypeView.text = item.fleetType.toString()
            longitudeTextView.text = item.coordinate?.longitude.toString()
            latitudeTextView.text = item.coordinate?.latitude.toString()
        }
    }
}