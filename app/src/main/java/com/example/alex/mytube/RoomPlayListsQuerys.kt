package com.example.alex.mytube

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface RoomPlayListsQuerys {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(videoTable: RoomVideoTable)

    @Query("SELECT * FROM video")
    fun getAllVideos(): LiveData<List<RoomVideoTable>>

    @Query("SELECT * FROM video WHERE play_list_name = :playListId")
    fun getVideosByPlayList(playListId : String): LiveData<List<RoomVideoTable>>


    @Query("SELECT video_id FROM video WHERE video_id = :s")
    fun checkVideoItem(s: String): String
}