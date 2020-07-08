package com.student.patient.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.patient.R
import com.student.patient.SpacesItemDecoration
import com.student.patient.fragment.DataViewModel
import kotlinx.android.synthetic.main.activity_data_detail.*

class DataDetailActivity : AppCompatActivity(){

    companion object{
        val HOMETYPE = "homeNumber"
        val TYPENUMBER = "numberNumber"
        val TITLE = "title"
        val ISHOME = "isHome"
        fun newInstance(context: Context,homeNumber:String,typeNumber:String,title:String,isHome:Boolean) {
            val intent = Intent(context, DataDetailActivity::class.java)
            intent.putExtra(HOMETYPE,homeNumber)
            intent.putExtra(TYPENUMBER,typeNumber)
            intent.putExtra(TITLE,title)
            intent.putExtra(ISHOME,isHome)
            context.startActivity(intent)
        }
    }


    private lateinit var viewModel: DataViewModel
    private lateinit var dataDetailAdapter:DataDetailAdapter
    private var ishome : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
        ishome = intent.getBooleanExtra(ISHOME, false)
        initView()
        initData()
    }



    private fun initView() {
        val titleText = intent.getStringExtra(TITLE) ?: return

        detail_title.setBackListener(null,this)
        detail_title.setTitle(titleText)
        dataDetailAdapter = DataDetailAdapter(ishome)
        detail_list.run {
            layoutManager = LinearLayoutManager(this@DataDetailActivity)
            adapter = dataDetailAdapter
            addItemDecoration(SpacesItemDecoration(4))
        }
    }

    private fun initData() {
        val typeNumber = intent.getStringExtra(TYPENUMBER) ?: return
        val homeNumber = intent.getStringExtra(HOMETYPE) ?: return
        if (ishome){
            viewModel.queryCheckTypeSickness(homeNumber).observe(this, Observer {
                dataDetailAdapter.addData(it)
            })
        }else{
            viewModel.queryTypeSickness(typeNumber).observe(this, Observer {
                dataDetailAdapter.addData(it)
            })
        }
    }
}