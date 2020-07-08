package com.student.patient.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sickness(@PrimaryKey(autoGenerate = true)val id:Int,
                    val name:String,
                    val url:String,
                    val type:String,
                    @ColumnInfo(name = "type_number")
                    val typeNumber:String,
                    val homeType:String,
                    var collect:Int)
