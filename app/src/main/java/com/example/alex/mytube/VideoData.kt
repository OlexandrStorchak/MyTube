package com.example.alex.mytube

class VideoData(val items: List<Videos>)


class Videos(val snippet: SnippetVideo)

class SnippetVideo(val title: String,
                   val description: String,
                   val thumbnails: VideoImg,
                   val resourceId: RecourceId,
                   val playlistId : String)

class VideoImg(val medium: MediumVideoImg)

class MediumVideoImg(val url: String)

class RecourceId(val videoId: String)