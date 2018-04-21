package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.LiveData

class Repository(val app: Application, val myRoom: MyRoomDB ) {


    fun getPlayLists(): LiveData<List<RoomPlayLists>> {

        val mRoomPlayListsQuerys: LiveData<List<RoomPlayLists>>
                = myRoom.RoomPlayListsQuerys().getPlayLists()

        return mRoomPlayListsQuerys
    }


}