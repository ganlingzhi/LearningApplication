package com.example.learningapplication.main.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learningapplication.R
import com.example.learningapplication.databinding.SimpleTvBinding

class SimpleAdapter : RecyclerView.Adapter<SimpleAdapter.MyViewHolder>() {

     val dataList:ArrayList<String> = arrayListOf()

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding:SimpleTvBinding? = null
        init {
            binding = SimpleTvBinding.bind(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.simple_tv, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding?.tv?.text = dataList[position]
    }
    fun setData(list: List<String>) {
        dataList.clear()
        dataList.addAll(list)
        notifyDataSetChanged()
    }

}