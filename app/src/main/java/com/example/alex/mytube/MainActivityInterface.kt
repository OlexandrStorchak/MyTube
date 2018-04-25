package com.example.alex.mytube

interface MainActivityInterface {
    fun saveToRoom(roomVideoTable: RoomVideoTable)
    fun openVideoActivity(videoId : String)
}