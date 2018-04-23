package com.example.alex.mytube

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface RoomPlayListsQuerys {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(playList: RoomPlayLists)

    @Query("SELECT * FROM room_play_lists")
    fun getPlayLists(): LiveData<List<RoomPlayLists>>

    @Insert
    fun addPlayList(roomPlayLists: RoomPlayLists)

    @Query("SELECT play_list_name FROM room_play_lists WHERE play_list_name = :s")
    fun checkPlayListItem(s: String): String
}