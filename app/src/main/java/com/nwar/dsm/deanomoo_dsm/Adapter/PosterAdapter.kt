package com.nwar.dsm.deanomoo_dsm.Adapter


import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.nwar.dsm.deanomoo_dsm.DataModule.Comment
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.Image.PicassoTransFormation
import com.nwar.dsm.deanomoo_dsm.R
import com.squareup.picasso.Picasso


class PosterAdapter (val context: Context, val items : ArrayList<Poster>):RecyclerView.Adapter<PosterAdapter.ViewHolder>(){

    lateinit var view : ViewHolder
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        Log.e("poster", "onCreateViewHolder")
        view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,p0,false))
        return view
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
    }

    override fun onBindViewHolder(p0: PosterAdapter.ViewHolder, p1: Int) {
        Log.e("poster", "onBindViewHolder")

        if(items[p1].picture!=null)
        {
            val picassoTransFormation = PicassoTransFormation()
            Picasso.with(context).load(items[p1].picture).transform(picassoTransFormation.resizeTransFormation).into(p0.picture)
        }

        p0.setRecyclerView(items[p1].commentList)
        p0.bind(items[p1],context)
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val title = view?.findViewById<TextView>(R.id.title_poster_tv)
        val content = view?.findViewById<TextView>(R.id.content_poster_tv)
        val picture = view?.findViewById<ImageView>(R.id.image_poster_iv)
        //val recyclerView = view?.findViewById<RecyclerView>(R.id.list_comment_list)
        fun bind(posterInfo : Poster, context: Context){
            title?.text = posterInfo.title
            content?.text = posterInfo.content

            Log.e("poster", "bind()")
        }
        fun addItem(dataInfo : Poster){
            items.add(itemCount,dataInfo)
            notifyItemInserted(itemCount)
        }
        fun setRecyclerView(commentList : ArrayList<Comment>?) {
            Log.e("setRecyclerView", "진입..")
            /*val adapter = CommentAdapter(context, commentList)
            val lm = CustomLinearLayoutManager(context)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = lm
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.setHasFixedSize(false)*/

            //val mInflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            //val inflate = mInflater.inflate
            Log.e("SetRecyclerView","끝")
        }
    }
}