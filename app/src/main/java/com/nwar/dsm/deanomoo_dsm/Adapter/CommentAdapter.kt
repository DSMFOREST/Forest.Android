package com.nwar.dsm.deanomoo_dsm.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nwar.dsm.deanomoo_dsm.DataModule.Comment
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.R

class CommentAdapter (val context: Context, val items : ArrayList<Comment>?) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.comment_item,p0,false))
        return view
    }

    override fun getItemCount(): Int {
        if(items!=null)return items.size
        else return 0
    }

    override fun onBindViewHolder(p0: CommentAdapter.ViewHolder, p1: Int) {
        if(items!=null)p0.bind(items[p1],context)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.comment_name_tv)
        val content = view.findViewById<TextView>(R.id.comment_content_tv)
        fun bind(posterInfo : Comment, context: Context){
            name.text = posterInfo.name
            content.text = posterInfo.content
        }
    }
}