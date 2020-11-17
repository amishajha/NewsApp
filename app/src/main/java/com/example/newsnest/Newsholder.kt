package com.example.newsnest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class Newsholder(val context: Context,val article: List<Article>):RecyclerView.Adapter<Newsholder.Articleviewholder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articleviewholder {
      val view=LayoutInflater.from(context).inflate(R.layout.itemlayout,parent,false)
        return Articleviewholder(view)
    }

    override fun getItemCount(): Int {
     return article.size
    }

    override fun onBindViewHolder(holder: Articleviewholder, position: Int) {
      val article=article[position]
   holder.newsTitle.text=article.title
        holder.newsdesc.text=article.description
         Glide.with(context).load(article.urlToImage).into(holder.newimage)
        holder.itemView.setOnClickListener {
            Toast.makeText(context,article.title,Toast.LENGTH_SHORT).show()
        }

    }
    class Articleviewholder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var newimage = itemView.findViewById<ImageView>(R.id.newsimage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newstitle)
        var newsdesc = itemView.findViewById<TextView>(R.id.newsdesc)
    }
}