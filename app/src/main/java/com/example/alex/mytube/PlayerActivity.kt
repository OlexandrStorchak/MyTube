package com.example.alex.mytube

import android.os.Bundle
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var mVideoId: String

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        playeId.initialize(API_KEY, this)
        val videoId = intent.getStringExtra("videoId")
        if (videoId != null) {
            mVideoId = videoId
        }

    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
        p1?.cueVideo(mVideoId)
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {

    }

}
