package com.example.alex.mytube

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.play_list_videos_item.view.*

class RVVideoAdapter(private var mVideo: List<RoomVideoTable>?,
                     private val mContext: Context,
                     private var mainActivityInterface: MainActivityInterface) : RecyclerView.Adapter<MyVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        return MyVH(LayoutInflater.from(mContext)
                .inflate(R.layout.play_list_videos_item, parent, false))
    }

    override fun getItemCount(): Int {
        return mVideo?.size!!
    }

    override fun onBindViewHolder(holder: MyVH, @SuppressLint("RecyclerView") position: Int) {
        mVideo!!.apply {
            holder.titleVideo.text = this[position].videoTitle
            holder.descVideo.text = this[position].videoDescription

            Picasso.get().load(this[position].videoImgUrl)
                    .into(holder.imageVideo)

            holder.recyclerItem.setOnClickListener {
                mainActivityInterface.openVideoActivity(this[position].videoId)

                mainActivityInterface.saveToRoom(RoomVideoTable(null,
                        this[position].playListId,
                        this[position].playListTitle,
                        this[position].videoTitle,
                        this[position].videoId,
                        this[position].videoDescription,
                        this[position].videoImgUrl,
                        null))
            }

        }

    }

    fun setVideo(t: List<RoomVideoTable>?) {
        mVideo = t
    }
}

class MyVH(view: View) : RecyclerView.ViewHolder(view) {
    val imageVideo = view.imageVideoItem!!
    val titleVideo = view.titleVideoItem!!
    val descVideo = view.decsVideoItem!!
    val recyclerItem = view.recyclerItem!!

}
