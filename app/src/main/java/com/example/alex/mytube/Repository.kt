package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.LiveData

class Repository(val app: Application) {


    fun getPlayLists() : LiveData<List<RoomPlayLists>>{
        val myRoom : MyRoomDB = MyRoomDB.getInstance(app)!!
         val mRoomPlayListsQuerys: LiveData<List<RoomPlayLists>>
                = myRoom.RoomPlayListsQuerys().getPlayLists()

        return mRoomPlayListsQuerys
    }

}