package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData


class PlayListViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: Repository = Repository(application, MyRoomDB.getInstance(application)!!)

    private var mVideos: LiveData<List<RoomVideoTable>>


    init {
        mVideos = mRepository.getVideoHttph(null)

    }


    fun getVideos(): LiveData<List<RoomVideoTable>> {
        if (mVideos == null) {
            mRepository.getVideoHttph(null)
            mVideos = mRepository.getMutableVideoList()
        }
        return mVideos
    }

    fun showVideoByPlayList(playListId: String): LiveData<List<RoomVideoTable>> {
        mVideos = mRepository.getVideoHttph(playListId)
        return mVideos
    }


}
