package com.nwar.dsm.deanomoo_dsm.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.LinearLayout
import com.nwar.dsm.deanomoo_dsm.Adapter.PosterAdapter
import com.nwar.dsm.deanomoo_dsm.DataModule.Comment
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.R
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection

class MainActivity : AppCompatActivity(), SwipyRefreshLayout.OnRefreshListener{

    lateinit var commentList : ArrayList<Comment>  // 테스트용(댓글리스트)
    var posterList = arrayListOf<Poster>()
    lateinit var posterAdapter: PosterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commentList = ArrayList<Comment>()
        commentList.add(Comment("유동근", "  부반장"))
        commentList.add(Comment("전찬훈","  반장"))
        commentList.add(Comment("유동근", "  부반장"))
        commentList.add(Comment("전찬훈","  반장"))
        commentList.add(Comment("유동근", "  부반장"))
        commentList.add(Comment("전찬훈","  반장"))
        commentList.add(Comment("유동근", "  부반장"))
        commentList.add(Comment("전찬훈","  반장"))

        setContentView(R.layout.activity_main)
        setEvent()
        setPosterList()
        posterAdapter = setRecyclerView()
    }

    fun setEvent(){
        val swipeRefresh = findViewById<SwipyRefreshLayout>(R.id.swipe_main_swipe)
        val recyclerView = findViewById<RecyclerView>(R.id.list_poster_list)
        swipeRefresh.setOnRefreshListener(this)
        recyclerView.setOnScrollChangeListener(object : View.OnScrollChangeListener{
            override fun onScrollChange(v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int) {
                Log.i("scroll","change")
                val endless = findViewById<LinearLayout>(R.id.endless_scroll_iv)
                if(!(recyclerView.canScrollVertically(1))){
                    Log.i("scroll","bottom")
                    endless.visibility = VISIBLE
                }
                else{
                    Log.i("scroll", "not bottom")
                    //endless.visibility = INVISIBLE
                }
            }
        })
    }

    fun setPosterList(){
        for (i in 1..10){
            posterList.add(Poster("$i"+"번째 대마", "대마숲 $i"+"번째", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Cyrillic_letter_A_-_uppercase_and_lowercase.svg/1200px-Cyrillic_letter_A_-_uppercase_and_lowercase.svg.png", commentList))
        }
    }

    fun setRecyclerView() : PosterAdapter {
        val adapter = PosterAdapter(this, posterList)
        Log.e("poster", "createAdapter")
        val lm = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.list_poster_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = lm
        Log.e("poster", "hasFixedSize")
        recyclerView.setHasFixedSize(true)
        return adapter
    }

    override fun onRefresh(direction : SwipyRefreshLayoutDirection) { // 스와이프 새로고침
        val URL = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Cyrillic_letter_A_-_uppercase_and_lowercase.svg/1200px-Cyrillic_letter_A_-_uppercase_and_lowercase.svg.png"
        posterAdapter.view.addItem(Poster("${posterAdapter.items.size+1}번째 대마(refresh)", "대마 ${posterAdapter.items.size+1}번째 대마", URL,commentList))
        val swipe = findViewById<SwipyRefreshLayout>(R.id.swipe_main_swipe)
        swipe.isRefreshing = false
    }
}