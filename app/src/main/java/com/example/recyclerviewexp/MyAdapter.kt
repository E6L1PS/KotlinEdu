package com.example.recyclerviewexp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val list: List<Person>) : RecyclerView.Adapter<MyAdapter.MyView>() {

    class MyView(item: View) : RecyclerView.ViewHolder(item) {
        val textViewName: TextView = item.findViewById(R.id.tvName)
        val textViewNumber: TextView = item.findViewById(R.id.tvNumber)
        val imageViewSex: ImageView = item.findViewById(R.id.ivSex)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyView(view)
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.textViewName.text = list[position].name
        holder.textViewNumber.text = list[position].phoneNumber
        holder.imageViewSex.setImageResource(
            when (list[position].sex) {
                'M' -> R.drawable.ic_man
                'W' -> R.drawable.ic_woman
                else -> R.drawable.ic_unknown
            }
        )
    }

    override fun getItemCount() = list.size
}