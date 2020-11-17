package com.example.newsnest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
   lateinit var adapter:Newsholder
    var pagenum=1
    var totalresults=-1
    private var articles = mutableListOf<Article>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recycler_view = findViewById<RecyclerView>(R.id.NewsList)
        adapter = Newsholder(this@MainActivity,articles)

        recycler_view.adapter=adapter
        //recycler_view.layoutManager=LinearLayoutManager(this@MainActivity)
        val layoutManager=StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)
recycler_view.layoutManager=layoutManager
        layoutManager.setItemChangedListener(object :StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
               if (totalresults>layoutManager.itemCount&&layoutManager.getFirstVisibleItemPosition()>=layoutManager.itemCount-5)
               {
                   pagenum++
                   getnews()
               }

            }
        })
  getnews()
    }
    private fun getnews() {
        val news = newservice.newsInstance.getHeadline("in", pagenum)
        news.enqueue(object : Callback<News> {
            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("code", "fetching", t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    Log.d("code", news.toString())
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()







                }
            }
        })
    }
}