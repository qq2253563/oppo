package com.student.patient.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.student.patient.R
import com.student.patient.SpacesItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*


class DataFragment : Fragment(){

    companion object{
        fun newInstance(): DataFragment {
            return DataFragment()
        }
    }

    private lateinit var viewModel : DataViewModel
    private lateinit var listAdapter: HomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }


    private fun initData() {
        viewModel.queryAllType().observe(viewLifecycleOwner, Observer {
            listAdapter.addData(it)
        })
    }

    private fun initView(){
        title.setTitle("数据库")
        auto_layout.showDefault()
        listAdapter = HomeListAdapter(false)
        list.run {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            addItemDecoration(SpacesItemDecoration(4))
        }
    }
}