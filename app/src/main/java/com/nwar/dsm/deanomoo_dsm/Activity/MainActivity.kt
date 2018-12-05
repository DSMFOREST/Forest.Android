package com.nwar.dsm.deanomoo_dsm.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nwar.dsm.deanomoo_dsm.Adapter.PosterAdapter
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.R
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection

class MainActivity : AppCompatActivity(), SwipyRefreshLayout.OnRefreshListener {

    var posterList = arrayListOf<Poster>()
    lateinit var posterAdapter: PosterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPosterList()
        posterAdapter = setRecyclerView()
        setEvent()
    }

    fun setEvent(){
        val swipeRefresh = findViewById<SwipyRefreshLayout>(R.id.swipe_main_swipe)
        swipeRefresh.setOnRefreshListener(this)
    }

    fun setPosterList(){
        for (i in 1..11){
            posterList.add(Poster("$i"+"번째 대마", "대마숲 $i"+"번째", null))
        }
    }

    fun setRecyclerView() : PosterAdapter {
        val adapter = PosterAdapter(this, posterList)
        val lm = LinearLayoutManager(this)
        val recyclerView = findViewById<RecyclerView>(R.id.list_poster_list)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = lm
        recyclerView.setHasFixedSize(true)
        return adapter
    }

    override fun onRefresh(direction : SwipyRefreshLayoutDirection) { // 스와이프 새로고침
        posterAdapter.view.addItem(Poster("${posterAdapter.items.size+1}번째 대마(refresh)", "대마 ${posterAdapter.items.size+1}번째 대마", null))
        val swipe = findViewById<SwipyRefreshLayout>(R.id.swipe_main_swipe)
        swipe.isRefreshing = false
    }

}