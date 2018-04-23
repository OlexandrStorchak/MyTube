package com.example.alex.mytube

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.alex.mytube.RoomPlayLists.Companion.PLAY_LIST_TABLE

@Entity(tableName = PLAY_LIST_TABLE)
class RoomPlayLists(@PrimaryKey(autoGenerate = true) var id: Int?, @ColumnInfo(name = "play_list_name") var playListId: String?, var playListTitle: String?) {
    companion object {
        const val PLAY_LIST_TABLE = "room_play_lists"
    }
}