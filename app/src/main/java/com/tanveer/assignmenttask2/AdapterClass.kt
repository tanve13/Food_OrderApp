package com.tanveer.assignmenttask2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AdapterClass(var arrayList: ArrayList<AdapterDataClass>): BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
      return arrayList[position].Quantity?.toLong()?:0L
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_data_base_adapter,
            parent,false)
        var tvItemName = view.findViewById<TextView>(R.id.tvItemName)
        var tvQuantity = view.findViewById<TextView>(R.id.tvQuantity)
        tvItemName.setText(arrayList[position].item)
        tvQuantity.setText(arrayList[position].Quantity.toString())
        return view
    }
}