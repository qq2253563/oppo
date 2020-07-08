package com.student.patient.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RawRes
import androidx.recyclerview.widget.RecyclerView
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
            holder.more.visibility = View.GONE
            holder.sign.visibility = View.VISIBLE
        } else {
            holder.more.visibility = View.VISIBLE
            holder.sign.visibility = View.GONE
        }
        if (data.isNullOrEmpty()) return
        if (isHome)
        when (data[position].homeType) {
          "101" ->{
                holder.sign.setBackgroundResource(R.drawable.blue)
                holder.text.text = "内科"
            }
           "102" -> {
                holder.sign.setBackgroundResource(R.drawable.pink)
                holder.text.text = "妇科"
            }
           "103" -> {
                holder.sign.setBackgroundResource(R.drawable.orange)
                holder.text.text = "儿科"
            }
            "104" -> {
                holder.sign.setBackgroundResource(R.drawable.red)
                holder.text.text = "外科"
            }
            "105" -> {
                holder.text.text = "其他疾病"
                holder.sign.setBackgroundResource(R.drawable.purple)
            }

        }
        else  holder.text.text = data[position].type
        holder.itemView.setOnClickListener {
            DataDetailActivity.newInstance(context, data[position].homeType, data[position].typeNumber,holder.text.text.toString(),isHome)
        }
    }

    inner class HomeListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.text)
        val sign = itemView.findViewById<View>(R.id.sign)
        val more = itemView.findViewById<View>(R.id.more)
    }

    fun addData(list: List<Sickness>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }
}