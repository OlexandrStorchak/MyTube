package com.example.alex.mytube

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.example.alex.mytube.RoomPlayListItems.Companion.PLAY_LIST_ITEMS_TABLE

@Entity(tableName = PLAY_LIST_ITEMS_TABLE)
class RoomPlayListItems(@PrimaryKey(autoGenerate = true) var id: Int,
                        var videoTitle: String,
                        var videoDescription: String,
                        var videoImage: String,

                        var videoPlayListname: String) {
    companion object {
        const val PLAY_LIST_ITEMS_TABLE = "play_lists_items"
    }
}