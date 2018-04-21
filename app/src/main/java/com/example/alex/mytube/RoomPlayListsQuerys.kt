package com.example.alex.mytube

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface RoomPlayListsQuerys {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playList: RoomPlayLists)

    @Query("SELECT * FROM room_play_lists")
    fun getPlayLists(): LiveData<List<RoomPlayLists>>

    @Insert
    fun addPlayList(roomPlayLists: RoomPlayLists)
}