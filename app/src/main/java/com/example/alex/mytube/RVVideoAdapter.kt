package com.example.alex.mytube

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.play_list_videos_item.view.*

class RVVideoAdapter(private var mVideo: List<RoomVideoTable>?,
                     private val mContext: Context) : RecyclerView.Adapter<MyVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(LayoutInflater.from(mContext)
                .inflate(R.layout.play_list_videos_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mVideo?.size!!
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {

        holder.titleVideo.text = mVideo?.get(position)?.videoTitle
        holder.descVideo.text = mVideo?.get(position)?.videoDescription

        Picasso.get().load(mVideo?.get(position)?.videoImgUrl).into(holder.imageVideo)

    }

    fun setVideo(t: List<RoomVideoTable>?) {
        mVideo = t
    }
}

class MyVH(view: View) : RecyclerView.ViewHolder(view) {
    val imageVideo = view.imageVideoItem!!
    val titleVideo = view.titleVideoItem!!
    val descVideo = view.decsVideoItem!!
}
