package com.example.alex.mytube

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var mPlayListViewModel: PlayListViewModel? = null
    private var videos: List<RoomPlayLists>? = ArrayList()
    private lateinit var rvVideoAdapter: RVVideoAdapter

    //Menu var for runtime adding items
    private lateinit var mNaviView: NavigationView
    private lateinit var mPlayLists: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Init NavDrawers and menu
        mNaviView = findViewById(R.id.nav_view)
        mPlayLists = mNaviView.menu

        //RecyclerView for display videos
        recyclerView.layoutManager = LinearLayoutManager(this)
        rvVideoAdapter = RVVideoAdapter(videos, this)
        recyclerView.adapter = rvVideoAdapter

            //TEMP
        fab.setOnClickListener {

            mPlayListViewModel!!.addPlayList()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)


        mPlayListViewModel = ViewModelProviders.of(this).get(PlayListViewModel::class.java)
        mPlayListViewModel!!.getPlayLists().observe(this, Observer<List<RoomPlayLists>> { t ->
            //Update list of play list
            mPlayLists.clear()
            if (t != null) {
                for (e in t) {
                    //Create or update list of menu with playlist names
                    //Toast.makeText(applicationContext,e.name,Toast.LENGTH_SHORT).show()
                    Log.d("log", e.name + " " + e.id)
                    rvVideoAdapter.setVideo(t)
                    rvVideoAdapter.notifyDataSetChanged()

                    mPlayLists.add(e.name)

                }
            }
        })

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.

        Toast.makeText(this,item.title,Toast.LENGTH_SHORT).show()

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
