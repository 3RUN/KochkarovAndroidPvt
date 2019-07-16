package by.itacademy.pvt.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.R
import by.itacademy.pvt.dz6.entity.Student

class Dz6ListAdapter(
    private var items: List<Student>,
    private val listener: ClickListener
) : RecyclerView.Adapter<Dz6ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dz6_student, parent, false)

        val holder = Dz6ViewHolder(view)

        holder.itemView.setOnClickListener {
            listener.onStudentClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(viewHolder: Dz6ViewHolder, position: Int) {
        viewHolder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(items: List<Student>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onStudentClick(student: Student)
    }
}