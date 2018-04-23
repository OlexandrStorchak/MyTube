package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.LiveData
import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class Repository(val app: Application, val myRoom: MyRoomDB) {


    fun getPlayLists(): LiveData<List<RoomPlayLists>> {

        return myRoom.roomPlayListsQuerys().getPlayLists()
    }

    fun getHttpPlayLists() {
        val url = "https://www.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCnExw5tVdA3TJeb4kmCd-JQ&fields=items(id%2Csnippet(thumbnails%2Fmedium%2Furl%2Ctitle))&key=AIzaSyBXosAYMJj3ihDjYCoxQvIfyFp1YttfhEk"
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
                for (item in playListsData.items) {

                    updateRoomPlayLists(item.id, item.snippet.title)
                }
            }

        })


    }

    fun updateRoomPlayLists(id: String, title: String) {
        if (myRoom.roomPlayListsQuerys().checkPlayListItem(id) != id) {
            myRoom.roomPlayListsQuerys().addPlayList(RoomPlayLists(null, id, title))

        }

    }

    fun updateNavDrawerInfo(){

        val url = ""
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
                for (item in playListsData.items) {

                    updateRoomPlayLists(item.id, item.snippet.title)
                }
            }

        })

    }

}