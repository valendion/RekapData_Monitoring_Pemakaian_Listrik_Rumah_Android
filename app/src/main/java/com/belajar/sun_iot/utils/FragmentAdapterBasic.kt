package com.belajar.sun_iot.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.belajar.sun_iot.ui.fragment.ThirdFragment
import com.belajar.sun_iot.ui.fragment.realtime.RealThreeFragment

class FragmentAdapterBasic(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val type: String) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int  = 1

    override fun createFragment(position: Int): Fragment {

        if (type == "static"){
            when(position){
                1 -> return ThirdFragment()
            }
            return ThirdFragment()
        }else{
            when(position){
                1 -> return RealThreeFragment()
            }
            return RealThreeFragment()
        }
    }
}