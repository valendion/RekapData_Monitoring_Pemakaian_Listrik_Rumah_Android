package com.belajar.sun_iot.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.belajar.sun_iot.ui.fragment.FirstFragment
import com.belajar.sun_iot.ui.fragment.FourthFragment
import com.belajar.sun_iot.ui.fragment.SecondFragment
import com.belajar.sun_iot.ui.fragment.ThirdFragment
import com.belajar.sun_iot.ui.fragment.realtime.RealFourFragment
import com.belajar.sun_iot.ui.fragment.realtime.RealOneFragment
import com.belajar.sun_iot.ui.fragment.realtime.RealThreeFragment
import com.belajar.sun_iot.ui.fragment.realtime.RealTwoFragment

class FragmentAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, private val type: String) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int  = 4

    override fun createFragment(position: Int): Fragment {

        if (type == "static"){
            when(position){
                1 -> return SecondFragment()
                2 -> return ThirdFragment()
                3 -> return FourthFragment()
            }
            return FirstFragment()
        }else{
            when(position){
                1 -> return RealTwoFragment()
                2 -> return RealThreeFragment()
                3 -> return RealFourFragment()
            }
            return RealOneFragment()
        }
    }
}