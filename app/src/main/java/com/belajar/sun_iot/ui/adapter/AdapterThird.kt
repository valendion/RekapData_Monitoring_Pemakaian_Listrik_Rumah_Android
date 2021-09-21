package com.belajar.sun_iot.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajar.sun_iot.data.ModelDummy
import com.belajar.sun_iot.databinding.ListDataRecapBinding

class AdapterThird : RecyclerView.Adapter<AdapterThird.MyViewHolder>() {

    private var mData: MutableList<ModelDummy> = mutableListOf()

    fun updateAdapter(data: ArrayList<ModelDummy>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }


    inner class MyViewHolder(val itemViewList: ListDataRecapBinding) :
        RecyclerView.ViewHolder(itemViewList.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder
    {
        val itemBinding = ListDataRecapBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder){
            itemViewList.apply {
                textCostValue.text = mData[position].biaya.toString()
                textElectricValue.text = mData[position].kwh.toString()
            }
        }
    }

    override fun getItemCount(): Int = mData.size ?: 0
}