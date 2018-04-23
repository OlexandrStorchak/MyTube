package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class Repository(val app: Application, val myRoom: MyRoomDB) {
    companion object {
        val API_KEY = "AIzaSyBXosAYMJj3ihDjYCoxQvIfyFp1YttfhEk"
    }

    fun getAllVideos(): LiveData<List<RoomVideoTable>> {

        return myRoom.roomPlayListsQuerys().getAllVideos()
    }



    fun getVideosByPlayList(playList : String): LiveData<List<RoomVideoTable>>{
        return myRoom.roomPlayListsQuerys().getVideosByPlayList(playList)
    }

    fun getHttpVideos() {

        val playListId = "PLkKunJj_bZefHRpkU-MF5YMfIOwZRRlg8"

        val url = " https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&maxResults=50&playlistId=$playListId&fields=items(snippet(description%2CplaylistId%2CresourceId%2FvideoId%2Cthumbnails%2Fmedium%2Furl%2Ctitle))&key=$API_KEY"
        val mRequest =
                Request.Builder()
                        .url(url)
                        .build()

        val mHttpClient = OkHttpClient()
        mHttpClient.newCall(mRequest).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("log", e.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val mBody = response?.body()?.string()
                val mGson = GsonBuilder().create()
                val videoData = mGson.fromJson(mBody, VideoData::class.java)
                for (item in videoData.items) {

                    if (myRoom.roomPlayListsQuerys()
                                    .checkVideoItem(item.snippet.resourceId.videoId)
                            != item.snippet.resourceId.videoId) {
                        myRoom.roomPlayListsQuerys().insert(RoomVideoTable(null,
                                item.snippet.playlistId, null,
                                item.snippet.title,
                                item.snippet.resourceId.videoId,
                                item.snippet.description,
                                item.snippet.thumbnails.medium.url
                                , null))

                    }

                }
            }

        })


    }

    fun getPlayLists() {

        val url = "https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCnExw5tVdA3TJeb4kmCd-JQ&fields=items(id%2Csnippet%2Ftitle)&key=$API_KEY"
        val mRequest =
                Request.Builder()
                        .url(url)
                        .build()

        val mHttpClient = OkHttpClient()
        mHttpClient.newCall(mRequest).enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                Log.d("log", e.toString())
            }

            override fun onResponse(call: Call?, response: Response?) {
                val mBody = response?.body()?.string()
                val mGson = GsonBuilder().create()
                val playListsData = mGson.fromJson(mBody, PlayListsData::class.java)


            }
        })
    }

}