package com.example.advancedkotlinm03assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.song_item_layout.view.*

class SongListAdapter(val songList: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_item_layout, parent, false)
        return SongViewHolder(view)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val data = songList[position]
        holder.songName.text = data.name
        holder.songArtist.text = data.artist
    }

    class SongViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val songName: TextView = view.tv_song_name
        val songArtist: TextView = view.tv_song_artist
    }
}