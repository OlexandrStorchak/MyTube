package com.example.alex.mytube

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "video")
class RoomVideoTable(@PrimaryKey(autoGenerate = true) var id: Int?,
                     @ColumnInfo(name = "play_list_name") var playListId: String?,
                     var playListTitle: String?,
                     var videoTitle: String,
                     @ColumnInfo(name = "video_id") var videoId : String,
                     var videoDescription: String,
                     var videoImgUrl: String,
                     var videoImageLocal: String?)