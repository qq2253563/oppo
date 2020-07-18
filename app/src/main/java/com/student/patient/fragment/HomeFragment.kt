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


class HomeFragment : Fragment(){

    companion object{
        fun newInstance(): HomeFragment {
            return HomeFragment()
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
        viewModel.queryAllCheckType().observe(viewLifecycleOwner, Observer {
            listAdapter.addData(it)
            if (listAdapter.itemCount>0){
                auto_layout.showDefault()
            }else{
                auto_layout.showError()
            }
        })
    }

    private fun initView(){
        title.setTitle("首页")
        listAdapter = HomeListAdapter(true)
        list.run {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
            addItemDecoration(SpacesItemDecoration(4))
        }
    }


}