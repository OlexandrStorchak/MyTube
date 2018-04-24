package com.example.alex.mytube

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData


class PlayListViewModel(application: Application) : AndroidViewModel(application) {

    private val mRepository: Repository = Repository(application, MyRoomDB.getInstance(application)!!)

    private var mVideos: MutableLiveData<LiveData<RoomVideoTable>>


    init {
        mVideos = mRepository.getAllVideos()


    }

    fun getVideos(): MutableLiveData<LiveData<RoomVideoTable>> {
        mVideos = mRepository.getAllVideos()

        mRepository.getHttpVideos()

        return mVideos
    }


    fun getPlayLists() {
        mRepository.getPlayLists()
    }

    /*fun showVideoByPlayList(playListId: String): LiveData<kotlin.collections.List<RoomVideoTable>> {
        mVideos = mRepository.getVideosByPlayList(playListId)
        return mVideos
    }
*/
}
