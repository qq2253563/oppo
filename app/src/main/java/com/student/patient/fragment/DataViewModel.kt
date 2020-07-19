package com.student.patient.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.student.patient.db.DB
import com.student.patient.db.Sickness

class DataViewModel(application: Application) : AndroidViewModel(application) {

    fun queryAllType():LiveData<List<Sickness>> = DB.getInstance().sicknessDao.getAllType()


    fun queryTypeSickness(typeNumber:String):LiveData<List<Sickness>> = DB.getInstance().sicknessDao.getTypeSickness(typeNumber)

    fun queryAllCheck():LiveData<List<Sickness>> = DB.getInstance().sicknessDao.getCheckAll()

    fun queryCheckTypeSickness(homeType:String):LiveData<List<Sickness>> = DB.getInstance().sicknessDao.getCheckTypeSickness(homeType)
}