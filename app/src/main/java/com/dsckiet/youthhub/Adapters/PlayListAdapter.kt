package com.dsckiet.youthhub.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.model.Item
import com.dsckiet.youthhub.model.Playlist
import com.squareup.picasso.Picasso
import okhttp3.Response

class PlayListAdapter (private val context: Context): RecyclerView.Adapter<PlayListAdapter.PlaylistViewModel>() {
    private var collectid:List<Item.Snippet> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewModel {
        val v= LayoutInflater.from(parent.context).inflate( R.layout.playlist_recview, parent, false)
        return PlayListAdapter.PlaylistViewModel(v)
    }

    override fun onBindViewHolder(holder: PlaylistViewModel, position: Int) {
        val collectid= collectid[position]

        holder?.title.text = collectid.title
        holder?.channelname.text = collectid.channelTitle
        Picasso.with(context)
            .load(collectid?.thumbnails?.default?.url)
            .into(holder.thumbnail)


        holder.itemView.setOnClickListener {
            Toast.makeText(context,"Clicked ", Toast.LENGTH_SHORT).show()
        }

    }





    override fun getItemCount(): Int {
        return collectid.size
    }

    fun PlaylistStateWiseTracker(list: List<Item.Snippet>){
        this.collectid=list
        notifyDataSetChanged()
    }


    class PlaylistViewModel(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.playlist_title)
        val thumbnail = itemView.findViewById<ImageView>(R.id.playlist_thumbnail)
        val channelname = itemView.findViewById<TextView>(R.id.playlist_channel_name)
        val channel_dp = itemView.findViewById<ImageView>(R.id.playlist_channel_dp)
    }

}
