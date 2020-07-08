package com.student.patient.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SicknessDao{


    //查询所有分类
    @Query("select * from Sickness group by type_number")
    fun checkData() : List<Sickness>

    //查询所有分类
    @Query("select * from Sickness group by type_number")
    fun getAllType() : LiveData<List<Sickness>>

    //查询所有选择分类
    @Query("select * from Sickness where collect = 1 group by homeType")
    fun getCheckAllType() : LiveData<List<Sickness>>

    //查询该分类所有数据
    @Query("select * from Sickness where type_number = :typeNumber")
    fun getTypeSickness(typeNumber:String) : LiveData<List<Sickness>>

    //查询该分类所有选择数据
    @Query("select * from Sickness where homeType = :homeType and collect = 1")
    fun getCheckTypeSickness(homeType:String) : LiveData<List<Sickness>>

    @Update
    fun updateCheck(sickness: Sickness)

    @Insert
    fun addSickness(sickness: MutableList<Sickness>)

    @Delete
    fun delSickness(sickness: Sickness)
}