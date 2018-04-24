package com.example.alex.mytube

import android.os.Bundle
import com.example.alex.mytube.Repository.Companion.API_KEY
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    lateinit var video: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val youtubeView: YouTubePlayerView = findViewById(R.id.playeId)
        youtubeView.initialize(API_KEY, this)
        val videoId = intent.getStringExtra("videoId")
        if (videoId != null) {
            video = videoId
        }

    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.cueVideo(video)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

    }

}
