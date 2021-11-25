package com.belajar.sun_iot.ui.fragment.realtime

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.belajar.sun_iot.databinding.ActivityRealtimeBinding
import com.belajar.sun_iot.ui.MainActivity
import com.belajar.sun_iot.ui.adapter.AdapterTablayout

class RealtimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRealtimeBinding
    private val adapterTablayout = AdapterTablayout()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRealtimeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.apply {

            backButton.setOnClickListener {
                startActivity(Intent(this@RealtimeActivity, MainActivity::class.java))
                finishAffinity()
            }

            adapterTablayout.callTablayout(
                this@RealtimeActivity,
                viewPagerRealtime,
                tabRealtime,
                this@RealtimeActivity,
                "real"
            )

        }
    }
}