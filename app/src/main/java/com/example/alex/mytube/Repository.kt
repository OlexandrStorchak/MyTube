package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

const val API_KEY = "AIzaSyBXosAYMJj3ihDjYCoxQvIfyFp1YttfhEk"

class Repository(val app: Application, val myRoom: MyRoomDB) {

    private var mListMutableVideo: MutableLiveData<List<RoomVideoTable>> = MutableLiveData()

    private lateinit var mListFromRoom: LiveData<List<RoomVideoTable>>

    fun getAllVideos(): LiveData<List<RoomVideoTable>> {

        return myRoom.roomPlayListsQuerys().getAllVideos()
    }

    fun getVideoHttph(playListId: String?): LiveData<List<RoomVideoTable>> {
        getHttpVideos(playListId)
        return mListMutableVideo
    }


    fun getVideosByPlayListRoom(playList: String): LiveData<List<RoomVideoTable>> {
        mListFromRoom = myRoom.roomPlayListsQuerys().getVideosByPlayList(playList)
        return mListFromRoom
    }

    private fun getHttpVideos(playListId: String?) {

        val id: String
        if (playListId == null) {
            id = "PLkKunJj_bZefHRpkU-MF5YMfIOwZRRlg8"
        } else {
            id = playListId
        }

        val url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=$id&fields=items(snippet(description%2CplaylistId%2CresourceId%2FvideoId%2Cthumbnails%2Fhigh%2Furl%2Ctitle))&key=$API_KEY"
        val mRequest =
                Request.Builder()
                        .url(url)
                        .build()

        val mHttpClient = OkHttpClient()
        mHttpClient.newCall(mRequest).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("log", "OFLINE FETCH VIDEO")

            }

            override fun onResponse(call: Call?, response: Response?) {
                val listVideos: MutableList<RoomVideoTable> = ArrayList()
                val mBody = response?.body()?.string()
                val mGson = GsonBuilder().create()
                val videoData = mGson.fromJson(mBody, VideoData::class.java)
                for (item in videoData.items) {

                    listVideos.add(element = RoomVideoTable(null,
                            item.snippet.playlistId, null,
                            item.snippet.title,
                            item.snippet.resourceId.videoId,
                            item.snippet.description,
                            item.snippet.thumbnails.high.url
                            , null))

                    addItem(listVideos)

                    /*if (myRoom.roomPlayListsQuerys()
                                    .checkVideoItem(item.snippet.resourceId.videoId)
                            != item.snippet.resourceId.videoId) {
                        myRoom.roomPlayListsQuerys().insert(RoomVideoTable(null,
                                item.snippet.playlistId, null,
                                item.snippet.title,
                                item.snippet.resourceId.videoId,
                                item.snippet.description,
                                item.snippet.thumbnails.medium.url
                                , null))

                    }*/

                }
            }

        })


    }

    fun addItem(element: List<RoomVideoTable>) {
        mListMutableVideo.postValue(element)
    }


}