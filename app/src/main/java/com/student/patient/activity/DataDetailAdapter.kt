package com.student.patient.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.heytap.wearable.support.widget.HeySingleItemWithCheckBox
import com.student.patient.R
import com.student.patient.db.DB
import com.student.patient.db.Sickness

class DataDetailAdapter(val isHome: Boolean) : RecyclerView.Adapter<DataDetailAdapter.Holder>() {

    private val data = mutableListOf<Sickness>()

    private lateinit var context:Context

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCheck = itemView.findViewById<HeySingleItemWithCheckBox>(R.id.data_check_item)
    }

    override fun getItemCount(): Int = data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataDetailAdapter.Holder {
        context = parent.context
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_data_detail, parent, false)
        return Holder(inflate)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (data.isNullOrEmpty()) return
        holder.itemCheck.a.maxLines = 3
        holder.itemCheck.setTitle(data[position].name)
        if (data[position].collect == 1) {
            holder.itemCheck.checkBox.state = 2
        } else {
            holder.itemCheck.checkBox.state = 0
        }
        holder.itemCheck.setOnClickListener {
            if (isHome){
                QRCodeActivity.newInstance(context,data[position].url,data[position].name)
                return@setOnClickListener
            }
            val sickness = data[position]
            sickness.time = System.currentTimeMillis()
            if(sickness.collect != 1) sickness.collect = 1 else sickness.collect = 0
            DB.getInstance().sicknessDao.updateCheck(sickness)
        }
    }

    fun addData(list:List<Sickness>){
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}