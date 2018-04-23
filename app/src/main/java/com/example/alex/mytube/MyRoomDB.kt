package com.example.alex.mytube

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [(RoomVideoTable::class)], version = 1)
abstract class MyRoomDB : RoomDatabase() {
    abstract fun roomPlayListsQuerys(): RoomPlayListsQuerys

    companion object {
        private var INSTANCE: MyRoomDB? = null

        fun getInstance(context: Context): MyRoomDB? {
            if (INSTANCE == null) {

                INSTANCE = Room.databaseBuilder(context,
                        MyRoomDB::class.java,
                        "data").build()

            }
            return INSTANCE
        }
    }

}