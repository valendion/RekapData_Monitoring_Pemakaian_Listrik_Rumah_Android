package com.belajar.sun_iot.utils

import java.text.NumberFormat
import java.util.*

object WritingFormat {

    fun formatRupiah(value: Int): String{
        val localeID = Locale("in", "ID")
        val formatRupiah: NumberFormat = NumberFormat.getCurrencyInstance(localeID)
        return formatRupiah.format(value)
    }

    fun formatVoltage(value: Int): String{
        return "$value volt"
    }

    fun formatCurrent(value: Int): String{
        return "$value A"
    }

    fun formatPower(value: Int): String{
        return "$value Watt"
    }

    fun formatPowerElectric(value: Int): String{
        return "$value KWH"
    }

}