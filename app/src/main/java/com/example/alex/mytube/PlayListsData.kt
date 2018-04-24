package com.example.alex.mytube

class PlayListsData(val items : List<PlayListsItems>)

class PlayListsItems(val id:String,val snippet : Snippet)

class Snippet(val title : String)