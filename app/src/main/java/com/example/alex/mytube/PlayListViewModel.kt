package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData


class PlayListViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: Repository = Repository(application, MyRoomDB.getInstance(application)!!)

    private var mVideos: LiveData<List<RoomVideoTable>>
    private var isLoad: Boolean

    init {
        mVideos = mRepository.getAllVideos()
        isLoad = true

    }


    fun getVideos(): LiveData<List<RoomVideoTable>> {
        return mVideos
    }

    fun showVideoByPlayList(playListId: String): LiveData<kotlin.collections.List<RoomVideoTable>> {
        mRepository.getHttpVideos(playListId)
        mVideos = mRepository.getVideosByPlayList(playListId)
        return mVideos
    }


}
