package com.nwar.dsm.deanomoo_dsm.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.nwar.dsm.deanomoo_dsm.DataModule.Comment
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.Image.PicassoTransFormation
import com.nwar.dsm.deanomoo_dsm.R
import com.squareup.picasso.Picasso
import android.view.View.VISIBLE

class PosterAdapter (val context: Context, val items : ArrayList<Poster>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    final val DEFAULT_ITEM = 0
    final val LAST_ITEM = 1

    lateinit var view : RecyclerView.ViewHolder

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        Log.e("poster", "onCreateViewHolder, ViewType: $p1")
        if(p1==LAST_ITEM){
            view = BottomViewHolder(LayoutInflater.from(context).inflate(R.layout.bottomof_recyclerview,p0,false))
        }
        else{
            view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item,p0,false))
        }
        return view
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        Log.e("poster", "onBindViewHolder, position : $p1")
        if(p0 is ViewHolder) {
            val holder: ViewHolder = p0
            if (items[p1].picture != null) {
                val picassoTransFormation = PicassoTransFormation()
                Picasso.with(context).load(items[p1].picture).transform(picassoTransFormation.resizeTransFormation)
                    .into(holder.picture)
                Log.e("imageURL", items[p1].picture)
            } else Log.e("imageURL", "null")
            holder.setOnClick(p1)
            holder.setRecyclerView(items[p1].commentList)
            holder.bind(items[p1], context)
        }
        else if(p0 is BottomViewHolder)
        {
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position == items.size-1)return LAST_ITEM
        else return DEFAULT_ITEM
    }

    inner class BottomViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun deleteItem(){
            notifyItemRemoved(items.size)
        }
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val title = view?.findViewById<TextView>(R.id.title_poster_tv)
        val content = view?.findViewById<TextView>(R.id.content_poster_tv)
        val picture = view?.findViewById<ImageView>(R.id.image_poster_iv)
        val reportBtn = view?.findViewById<ImageView>(R.id.report_poster_iv)
        val countComment = view?.findViewById<TextView>(R.id.list_countcomment_tv)
        var isShowComment : Boolean = false
        val recyclerView = view?.findViewById<RecyclerView>(R.id.list_comment_list)
        lateinit var posterInfo : Poster
        fun bind(posterInfo : Poster, context: Context){
            title?.text = posterInfo.title.toString() + "번째 대마"
            content?.text = posterInfo.content
            isShowComment = false
            this.posterInfo = posterInfo

            if(posterInfo.commentList==null) {
                countComment.text = "댓글이 없습니다."
            } else if(posterInfo.commentList.size==0){
                countComment.text = "댓글이 없습니다."
            } else {
                if (isShowComment) {
                    countComment.text = posterInfo.commentList.size.toString() + "개의 댓글 ▼"
                    recyclerView.visibility = VISIBLE
                } else {
                    countComment.text = posterInfo.commentList.size.toString() + "개의 댓글 ▶"
                    recyclerView.visibility = GONE
                }
            }

            Log.e("poster", "bind()")
        }
        fun setOnClick(number : Int){
            reportBtn.setOnClickListener {
                Toast.makeText(context,"${number+1}신고되었습니다.",Toast.LENGTH_SHORT).show()
            }
            countComment.setOnClickListener {
                isShowComment = !isShowComment
                Toast.makeText(context,"댓글창 열기/닫기, ${isShowComment}", Toast.LENGTH_SHORT).show()
                if(isShowComment){
                    countComment.text = posterInfo.commentList?.size.toString() + "개의 댓글 ▼"
                    recyclerView.visibility = VISIBLE
                }
                else{
                    countComment.text = posterInfo.commentList?.size.toString() + "개의 댓글 ▶"
                    recyclerView.visibility = GONE
                }
            }
        }
        fun setRecyclerView(commentList : ArrayList<Comment>?) {
            Log.e("setRecyclerView", "진입..")
            val adapter = CommentAdapter(context, commentList)
            val lm = CustomLinearLayoutManager(context)

            recyclerView.adapter = adapter
            recyclerView.layoutManager = lm
            recyclerView.isNestedScrollingEnabled = false
            recyclerView.setHasFixedSize(false)
            Log.e("SetRecyclerView","끝")
        }
    }
    fun addItem(dataInfo : Poster){
        items.add(itemCount,dataInfo)
        notifyItemInserted(itemCount)
    }
    override fun getItemCount(): Int {
        return items.size
    }
}