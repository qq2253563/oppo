package com.student.patient.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.heytap.wearable.support.widget.HeySingleDefaultItem
import com.heytap.wearable.support.widget.HeySingleItemWithCheckBox
import com.student.patient.R
import com.student.patient.activity.DataDetailActivity
import com.student.patient.activity.QRCodeActivity
import com.student.patient.db.Sickness

class HomeListAdapter(val isHome: Boolean) :

    RecyclerView.Adapter<HomeListAdapter.HomeListHolder>() {

    private val data = mutableListOf<Sickness>()

    override fun getItemCount(): Int = data.size

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeListHolder {
        context = parent.context
        val inflate =
            LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return HomeListHolder(inflate)
    }


    override fun onBindViewHolder(holder: HomeListHolder, position: Int) {
        if (isHome) {
            holder.more.primaryImage.visibility = View.GONE
            holder.sign.visibility = View.VISIBLE
        } else {
            holder.more.visibility = View.VISIBLE
            holder.sign.visibility = View.GONE
        }
        holder.more.a.maxLines = 3
        if (data.isNullOrEmpty()) return
        if (isHome){
            holder.more.setTitle(data[position].name)
            when (data[position].homeType) {
                "101" ->{
                    holder.sign.setBackgroundResource(R.drawable.blue)
                }
                "102" -> {
                    holder.sign.setBackgroundResource(R.drawable.pink)
                }
                "103" -> {
                    holder.sign.setBackgroundResource(R.drawable.orange)
                }
                "104" -> {
                    holder.sign.setBackgroundResource(R.drawable.red)
                }
                "105" -> {
                    holder.sign.setBackgroundResource(R.drawable.purple)
                }
            }
        }
        else  holder.more.setTitle(data[position].type)
        holder.more.setOnClickListener {
            if(isHome){
                QRCodeActivity.newInstance(context,data[position].url,data[position].name)
            }else{
                DataDetailActivity.newInstance(context, data[position].homeType, data[position].typeNumber,holder.more.a.text.toString(),isHome)
            }
        }
    }

    inner class HomeListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sign = itemView.findViewById<View>(R.id.sign)
        val more = itemView.findViewById<HeySingleDefaultItem>(R.id.more)
        val root = itemView.findViewById<ConstraintLayout>(R.id.root)
    }

    fun addData(list: List<Sickness>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}