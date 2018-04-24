package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData


class PlayListViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: Repository = Repository(application, MyRoomDB.getInstance(application)!!)

    private var mVideos: LiveData<List<RoomVideoTable>>


    init {
        mVideos = mRepository.getVideoHttph(null)

    }


    fun getVideosFromNetwork(): LiveData<List<RoomVideoTable>> {

        return mVideos
    }

    fun getVideosFromRoom(): LiveData<List<RoomVideoTable>> {
        mVideos = mRepository.getAllVideos()
        return mVideos
    }

    fun showVideoByPlayListNetwork(playListId: String): LiveData<List<RoomVideoTable>> {
        mVideos = mRepository.getVideoHttph(playListId)
        return mVideos
    }

    fun showVideoByPlayListRoom(playListId: String): LiveData<List<RoomVideoTable>> {
        mVideos = mRepository.getVideosByPlayListRoom(playListId)
        return mVideos
    }

}
