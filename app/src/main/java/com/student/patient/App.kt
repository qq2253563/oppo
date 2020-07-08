package com.student.patient

import android.app.Application
import com.student.patient.db.DB
import java.util.*

class App : Application() {

    companion object{
        var instance : App? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}