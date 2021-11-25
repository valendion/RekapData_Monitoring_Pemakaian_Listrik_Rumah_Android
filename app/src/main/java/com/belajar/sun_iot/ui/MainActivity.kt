package com.belajar.sun_iot.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.belajar.sun_iot.R
import com.belajar.sun_iot.data.ModelTotal
import com.belajar.sun_iot.databinding.ActivityMainBinding
import com.belajar.sun_iot.ui.adapter.AdapterTablayout
import com.belajar.sun_iot.ui.fragment.realtime.RealtimeActivity
import com.belajar.sun_iot.utils.NetworkConfig
import com.belajar.sun_iot.utils.WritingFormat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapterTablayout = AdapterTablayout()
    private var fragmentRefreshListener: FragmentRefreshListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            setSupportActionBar(toolbarMain)
            supportActionBar!!.title = ""

            pbMain.root.visibility = View.VISIBLE

            getTotal()

            swLayout.setOnRefreshListener {
                Handler(Looper.getMainLooper()).postDelayed({
                    swLayout.isRefreshing = false
                    getTotal()
                    if (getFragmentRefreshListener() != null) {
                        getFragmentRefreshListener()?.onRefresh()
                        getTotal()
                    }

//                    getTotal()

                }, 3000)
            }

            adapterTablayout.callTablayout(
                this@MainActivity,
                viewPagerMain,
                tabMain,
                this@MainActivity, "static"
            )

        }
    }

    private fun getFragmentRefreshListener(): FragmentRefreshListener? {
        return fragmentRefreshListener
    }

    fun setFragmentRefreshListener(fragmentRefreshListener: FragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener
    }

    interface FragmentRefreshListener {
        fun onRefresh()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
//            R.id.menuRealtime -> {
//                startActivity(Intent(this@MainActivity, RealtimeActivity::class.java))
//            }

            R.id.menuAbout -> {
                startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getTotal() {
        binding.apply {
            NetworkConfig.instance.getAllData().enqueue(object : Callback<ModelTotal> {
                override fun onResponse(call: Call<ModelTotal>, response: Response<ModelTotal>) {
//                    LoadingList.showLoading(0, pbMain.pbLoading)
                    pbMain.root.visibility = View.INVISIBLE

                    if (response.isSuccessful) {
                        val data = response.body()

                        if (data != null) {

                            textDataListrik.text = data.kwh?.let {
                                WritingFormat.formatPowerElectric(
                                    it
                                )
                            }
                            textBiaya.text = data.biaya?.let {
                                WritingFormat.formatRupiah(
                                    it
                                )
                            }

                            textDaya.text = data.daya?.let {
                                WritingFormat.formatPower(
                                    it
                                )
                            }

                        }
                    }
                }

                override fun onFailure(call: Call<ModelTotal>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Error $t", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

}