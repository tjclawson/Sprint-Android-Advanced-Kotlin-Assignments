package com.example.advancedkotlinm03assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {


    lateinit var songList: List<Song>
    lateinit var recyclerView: RecyclerView

    private val jazzList by lazy {
        listOf(
            Jazz("Song For My Father", "Horace Silver"),
            Jazz("Footprints", "Miles Davis"),
            Jazz("Young and Foolish", "Bill Evans")
        )
    }

    private val funkList by lazy {
        listOf(
            Funk("I'll Stay", "Funkadelic"),
            Funk("What Is Hip?", "Tower of Power"),
            Funk("Funky Drummer", "James Brown")
        )
    }

    private val fusionList by lazy {
        listOf(
            Fusion("Escher Sketcher", "Michael Brecker"),
            Fusion("Chank", "John Scofield"),
            Fusion("Lingus", "Snarky Puppy")
        )
    }


    private var listType: Song by Delegates.observable(Jazz("", "")) {
        _, _: Song, new: Song ->
        songList = when(new) {
            is Jazz -> jazzList
            is Funk -> funkList
            is Fusion -> fusionList
        }
        Log.i("BIGBRAIN", songList[1].artist)
        recyclerView.adapter = SongListAdapter(songList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        songList = listOf(Jazz("test", "test"))


        recyclerView = findViewById<RecyclerView>(R.id.recycler).apply {
            setHasFixedSize(false)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = SongListAdapter(songList)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_genre, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.menu_jazz -> { listType = Jazz("", "")}
            R.id.menu_funk -> { listType = Funk("","")}
            R.id.menu_fusion -> { listType = Fusion("","")}
        }
        return super.onOptionsItemSelected(item)
    }
}
