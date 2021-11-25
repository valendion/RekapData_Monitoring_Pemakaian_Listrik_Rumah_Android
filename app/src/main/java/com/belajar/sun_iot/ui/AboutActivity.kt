package com.belajar.sun_iot.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.belajar.sun_iot.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.apply {

            backButton.setOnClickListener {
                startActivity(Intent(this@AboutActivity, MainActivity::class.java))
                finishAffinity()
            }

        }
    }
}