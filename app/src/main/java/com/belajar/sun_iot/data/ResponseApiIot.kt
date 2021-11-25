package com.belajar.sun_iot.data

import retrofit2.Call
import retrofit2.http.GET

interface ResponseApiIot {

    companion object{
        const val  WATTMETER_AC = "wattmeter_ac"
        const val  WATTMETER_LAMP = "wattmeter_lampu"
        const val  WATTMETER_KULKAS = "wattmeter_kulkas"
        const val  WATTMETER_UNI = "wattmeter_uni"
        const val  WATTMETER = "wattmeter"


        const val  WEEK = "week"
        const val  TODAY = "today"
        const val  HOUR = "1hour"
        const val  ALLDAY = "allday"

    }

    @GET("$WATTMETER_AC/$WEEK")
    fun getACWeek(): Call<ModelResponseRecap>

    @GET("$WATTMETER_AC/$TODAY")
    fun getACToday(): Call<ModelResponseRecap>

    @GET("$WATTMETER_AC/$HOUR")
    fun getACHour(): Call<ModelResponseRecap>



    @GET("$WATTMETER_LAMP/$WEEK")
    fun getLampWeek(): Call<ModelResponseRecap>

    @GET("$WATTMETER_LAMP/$TODAY")
    fun getLampToday(): Call<ModelResponseRecap>

    @GET("$WATTMETER_LAMP/$HOUR")
    fun getLampHour(): Call<ModelResponseRecap>


    @GET("$WATTMETER_KULKAS/$WEEK")
    fun getKulkasWeek(): Call<ModelResponseRecap>

    @GET("$WATTMETER_KULKAS/$TODAY")
    fun getKulkasToday(): Call<ModelResponseRecap>

    @GET("$WATTMETER_KULKAS/$HOUR")
    fun getKulkasHour(): Call<ModelResponseRecap>


    @GET("$WATTMETER_UNI/$WEEK")
    fun getUniWeek(): Call<ModelResponseRecap>

    @GET("$WATTMETER_UNI/$TODAY")
    fun getUniToday(): Call<ModelResponseRecap>

    @GET("$WATTMETER_UNI/$HOUR")
    fun getUniHour(): Call<ModelResponseRecap>

    @GET("$WATTMETER/$ALLDAY")
    fun getAllData(): Call<ModelTotal>


}