package com.project.laundrykotlin.ui.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.laundrykotlin.R
import com.project.laundrykotlin.ui.database.FormData

class FormDataAdapter : RecyclerView.Adapter<FormDataAdapter.ViewHolder>() {
    private var formDataList = emptyList<FormData>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_item_name)
        val number: TextView = itemView.findViewById(R.id.tv_item_number)
        val address: TextView = itemView.findViewById(R.id.tv_item_address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = formDataList[position]
        holder.name.text = currentItem.name
        holder.number.text = currentItem.number
        holder.address.text = currentItem.address

        holder.itemView.setOnClickListener {
            val number = currentItem.number
            val url = "https://api.whatsapp.com/send?phone=$number"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            holder.itemView.context.startActivity(i)
        }

    }

    override fun getItemCount(): Int {
        return formDataList.size
    }

    fun setData(formData: List<FormData>) {
        this.formDataList = formData
        notifyDataSetChanged()
    }
}