package com.dsckiet.youthhub.RoomDatabaseWork.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.RoomDatabaseWork.Entity.PlaylistEntity
import com.example.youthhub.ui.HomeFragment
import com.squareup.picasso.Picasso

class HomeOfflinePlaylistAdapter (val context: Context, val listener: HomeFragment): RecyclerView.Adapter<HomeOfflinePlaylistAdapter.PlaylistViewHolder>() {

    val allPlaylists = ArrayList<PlaylistEntity>()

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val playlist_title = itemView.findViewById<TextView>(R.id.playlist_title)
        val playlists_channel_title = itemView.findViewById<TextView>(R.id.playlist_channel_name)
        val playlist_total_videos = itemView.findViewById<TextView>(R.id.videos_in_playlist)
        val playlist_thumbnail = itemView.findViewById<ImageView>(R.id.playlist_thumbnail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val viewHolder = PlaylistViewHolder(LayoutInflater.from(context).inflate(R.layout.playlist_recview,parent,false))

        return viewHolder
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val currentPlaylist = allPlaylists[position]
        holder.playlist_title.text = currentPlaylist.Playlist_Title
        holder.playlist_total_videos.text = currentPlaylist.Number_Of_Videos + " videos"
        holder.playlists_channel_title.text = currentPlaylist.Playlist_Channel_Name
        Picasso.with(context)
            .load(currentPlaylist.Playlist_Thumbnail)
            .into(holder.playlist_thumbnail)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("PlaylistVideoList_PlaylistID",currentPlaylist.Playlist_ID)
            it.findNavController().navigate(R.id.action_homeFragment_to_playlistVideoListFragment,bundle)
        }
        /* holder.itemView.setOnClickListener {
             val name = currentPlaylist.name
             val tagline = currentPlaylist.tagline
             val flag = "true"
             val date = currentfriend.dob
             val image = currentfriend.image
             var itembundle = Bundle()
             itembundle.putString("Adnamebundle",name)
             itembundle.putString("Adtaglinebundle",tagline)
             itembundle.putString("Addobbundle",date)
             itembundle.putString("Adflag",flag)
             itembundle.putString("Adimage", image)


             holder.itemView.findNavController().navigate(R.id.action_main2Fragment_to_onFriendClickedFragment,itembundle)*/
        }

    override fun getItemCount(): Int {
        return allPlaylists.size

    }

    fun deleteItem(i:Int){
        allPlaylists.removeAt(i)
        listener.onItemClicked(allPlaylists[i])
        notifyDataSetChanged()
    }

    fun updatePlaylistList(newlist:List<PlaylistEntity>){
        allPlaylists.clear()
        allPlaylists.addAll(newlist)
        notifyDataSetChanged()
    }

}

interface INotesRvAdapter{
    fun onItemClicked(playlistEntity: PlaylistEntity)
}