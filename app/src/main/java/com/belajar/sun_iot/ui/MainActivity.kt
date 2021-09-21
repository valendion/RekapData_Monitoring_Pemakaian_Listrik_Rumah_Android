package com.belajar.sun_iot.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.belajar.sun_iot.R
import com.belajar.sun_iot.databinding.ActivityMainBinding
import com.belajar.sun_iot.ui.adapter.AdapterTablayout
import com.belajar.sun_iot.ui.fragment.realtime.RealtimeActivity
import com.belajar.sun_iot.utils.FragmentAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterFragment: FragmentAdapter
    private val adapterTablayout = AdapterTablayout()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            setSupportActionBar(toolbarMain)
            supportActionBar!!.title = ""

            adapterTablayout.callTablayout(
                this@MainActivity,
                viewPagerMain,
                tabMain,
                this@MainActivity, "static"
            )

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.menuRealtime -> {
                startActivity(Intent(this@MainActivity, RealtimeActivity::class.java))
            }
            R.id.menuAbout -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

}