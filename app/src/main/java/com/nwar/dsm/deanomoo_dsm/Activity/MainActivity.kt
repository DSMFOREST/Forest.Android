package com.nwar.dsm.deanomoo_dsm.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.nwar.dsm.deanomoo_dsm.Adapter.PosterAdapter
import com.nwar.dsm.deanomoo_dsm.DataModule.Poster
import com.nwar.dsm.deanomoo_dsm.R

class MainActivity : AppCompatActivity() {

    var posterList = arrayListOf<Poster>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPosterList()
        val posterAdapter = setRecyclerView()
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
}