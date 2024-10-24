package com.example.lab4_shop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class Restaurant (val id:Int, val name: String)
class CustomAdapter (val context: Context, val items: ArrayList<Restaurant>): BaseAdapter(){
    override fun getCount(): Int {
        return items.size
    }
    override fun getItem(position: Int): Any {
        return items.size
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // LayoutInflater is used!
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.item, null)

        var imageView = view.findViewById<ImageView>(R.id.itemimageView)
        var textView = view.findViewById<TextView>(R.id.itemTextView)

        textView.text = items[position].name
        imageView.setImageResource(items[position].id)

        return view
    }
}