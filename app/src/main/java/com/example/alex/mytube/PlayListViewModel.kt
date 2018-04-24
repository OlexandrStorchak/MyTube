package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.net.ConnectivityManager


class PlayListViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: Repository = Repository(application, MyRoomDB.getInstance(application)!!)

    private var mVideos: LiveData<List<RoomVideoTable>>


    init {
        mVideos = mRepository.getAllVideos()


    }

    fun getVideos(): LiveData<List<RoomVideoTable>> {
        mVideos = mRepository.getAllVideos()

        mRepository.getHttpVideos()

        return mVideos
    }


    fun getPlayLists() {
        mRepository.getPlayLists()
    }

    fun showVideoByPlayList(playListId: String): LiveData<kotlin.collections.List<RoomVideoTable>> {
        mVideos = mRepository.getVideosByPlayList(playListId)
        return mVideos
    }


}
