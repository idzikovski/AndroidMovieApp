package com.example.inworks2.movieapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import java.net.URL
import io.reactivex.Observable


class MoviesAdapter(val items : List<Result>, val context : Context) : RecyclerView.Adapter<MoviesViewHolder>(){


    override fun onBindViewHolder(viewHolder: MoviesViewHolder, position: Int) {
        viewHolder.itemTitle.text=items[position].title
        viewHolder.itemDetail.text=items[position].overview

        var imageRequest = ImageRequest()
        imageRequest.imageView=viewHolder.itemImage
        imageRequest.uri=MyApplication.config?.images?.base_url + MyApplication.config?.images!!.poster_sizes[3] + "/" + items[position].poster_path
        val task = FetchImage().execute(imageRequest)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): MoviesViewHolder {
        val view=LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return  MoviesViewHolder(view)
    }


    override fun getItemCount(): Int {
        return items.count()
    }


}
class  MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var itemImage: ImageView = itemView.findViewById(R.id.item_image)
    var itemTitle: TextView = itemView.findViewById(R.id.item_title)
    var itemDetail: TextView = itemView.findViewById(R.id.item_detail)
}
