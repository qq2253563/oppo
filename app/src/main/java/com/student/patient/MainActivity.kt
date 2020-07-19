package com.student.patient

import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.student.patient.db.DB
import com.student.patient.db.Sickness
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Looper.myQueue().addIdleHandler {
            val checkData = DB.getInstance().sicknessDao.checkData()
            if (checkData.isNullOrEmpty()){
                getCSV()
            }
            return@addIdleHandler false
        }
        initView()
    }

    private fun initView() {
        main_viewpager.adapter = MainFragmentPagerAdapter(supportFragmentManager)
        main_indicator.setViewPager(main_viewpager)
    }


    private fun getCSV() {
        val dataLists = mutableListOf<Sickness>()
        try {
            val intPut = InputStreamReader(resources.assets.open("sickness.csv"))
            val csv = BufferedReader(intPut)
            var line:String?
            var i = 1
            do {
                line = csv.readLine()
                if (line != null){
                    // 把一行数据分割成多个字段
                    val split = line.split(",")
                    dataLists.add(Sickness(i,split[0],split[1],split[2],split[3],split[4],0,0))
                    i++
                }else{
                    break
                }
            }while (true)
            DB.getInstance().sicknessDao.addSickness(dataLists)
        } catch (e: FileNotFoundException) {
            e.printStackTrace();
        } catch (e: IOException) {
            e.printStackTrace();
        }
    }
}
