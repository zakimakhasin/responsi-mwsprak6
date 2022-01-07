package com.example.mwsprak6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mwsprak6.api.response.Participant

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var data: ArrayList<Participant> = arrayListOf()
    private lateinit var onClick : OnClickUpdate

    fun onClickListener (onClick : OnClickUpdate){
        this.onClick = onClick
    }

    fun setData(data: List<Participant>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvNim = itemView.findViewById<TextView>(R.id.tvNim)
        val tvName = itemView.findViewById<TextView>(R.id.tvNama)
        val tvProdi = itemView.findViewById<TextView>(R.id.tvProdi)
        val tvJenisKelamin = itemView.findViewById<TextView>(R.id.tvJenisKelamin)
        val btnEdit = itemView.findViewById<ImageView>(R.id.btnEdit)
        val btnDel = itemView.findViewById<ImageView>(R.id.btnDel)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_webinar, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = data[position]
        holder.tvNim.text = itemData.nim
        holder.tvName.text = itemData.nama
        holder.tvProdi.text = itemData.prodi
        holder.tvJenisKelamin.text = itemData.jenisKelamin
        holder.btnEdit.setOnClickListener {
            onClick.OnUdapete(itemData)
        }
        holder.btnDel.setOnClickListener {

            onClick.OnDelete(itemData)
        }

    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    interface OnClickUpdate{
        fun OnUdapete(data: Participant)
        fun OnDelete (data: Participant)
    }
}