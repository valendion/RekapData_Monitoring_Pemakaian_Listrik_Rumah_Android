package com.belajar.sun_iot.utils

import java.text.NumberFormat
import java.util.*

object WritingFormat {

    fun formatRupiah(value: Double): String{
        val localeID = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(value)
    }

    fun formatVoltage(value: Double): String{
        return "$value volt"
    }

    fun formatCurrent(value: Double): String{
        return "$value A"
    }

    fun formatPower(value: Double): String{
        return "$value Watt"
    }

    fun formatPowerElectric(value: Double): String{
        return "$value KWH"
    }

}