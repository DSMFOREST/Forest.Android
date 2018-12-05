package com.nwar.dsm.deanomoo_dsm.Adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.R


class PosterAdapter (val context: Context, val items : ArrayList<Poster>):RecyclerView.Adapter<PosterAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view : ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,p0,false))
        return view
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: PosterAdapter.ViewHolder, p1: Int) {
        p0.bind(items[p1],context)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val title = view?.findViewById<TextView>(R.id.title_poster_tv)
        val content = view?.findViewById<TextView>(R.id.content_poster_tv)
        val picture = view?.findViewById<ImageView>(R.id.image_poster_iv)
        fun bind(posterInfo : Poster, context: Context){
            title?.text = posterInfo.title
            content?.text = posterInfo.content
            picture.setOnClickListener{
                addItem(Poster("title", "content", null))
            }
        }
        fun addItem(dataInfo : Poster){
            items.add(itemCount,dataInfo)
            notifyItemInserted(itemCount)
        }
    }
}