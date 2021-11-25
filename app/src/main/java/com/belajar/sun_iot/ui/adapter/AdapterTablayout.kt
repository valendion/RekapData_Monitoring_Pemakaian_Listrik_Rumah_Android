package com.belajar.sun_iot.ui.adapter

import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.belajar.sun_iot.utils.FragmentAdapterBasic
import com.google.android.material.tabs.TabLayout

class AdapterTablayout()  {

    private lateinit var adapterFragment: FragmentAdapterBasic

    fun callTablayout(contextFragmentActivity: FragmentActivity,viewPager2: ViewPager2, tablayout: TabLayout, componentActivity: ComponentActivity, type: String) {

        val fe: FragmentManager = contextFragmentActivity.supportFragmentManager
            adapterFragment = FragmentAdapterBasic(fe, componentActivity.lifecycle, type)
        viewPager2.adapter = adapterFragment

//        tablayout.addTab(tablayout.newTab().setText("Lampu"))
//        tablayout.addTab(tablayout.newTab().setText("AC"))
        tablayout.addTab(tablayout.newTab().setText("Universal"))
//        tablayout.addTab(tablayout.newTab().setText("Kulkas"))
        
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{

            override fun onTabSelected(tab: TabLayout.Tab) {

                viewPager2.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
      
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                
            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                tablayout.selectTab(tablayout.getTabAt(position))
            }
        })

    }

}