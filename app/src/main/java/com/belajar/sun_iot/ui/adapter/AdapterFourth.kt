package com.belajar.sun_iot.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.belajar.sun_iot.data.ModelDummy
import com.belajar.sun_iot.data.ModelRecap
import com.belajar.sun_iot.databinding.ListDataRecapBinding
import com.belajar.sun_iot.utils.WritingFormat

class AdapterFourth : RecyclerView.Adapter<AdapterFourth.MyViewHolder>() {

    private var mData: MutableList<ModelRecap> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(data: ArrayList<ModelRecap>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearItem(){
        mData.clear()
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
                textCostValue.text =
                    mData[position].biaya?.let { WritingFormat.formatRupiah(it.toInt()) }
                textElectricValue.text = mData[position].kwh?.let {
                    WritingFormat.formatPowerElectric(
                        it.toInt()
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = mData.size ?: 0
}