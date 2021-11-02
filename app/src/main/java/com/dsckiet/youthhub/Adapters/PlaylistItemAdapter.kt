package com.dsckiet.youthhub.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.manager.Lifecycle
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.ViewModel.PlaylistViewModel
import com.dsckiet.youthhub.ViewModel.PlaylistViewModelFactory
import com.dsckiet.youthhub.model.Item
import com.dsckiet.youthhub.model.Snippet
import com.example.youthhub.Repo.Repository
import com.squareup.picasso.Picasso

class PlaylistItemAdapter(private val context: Context): RecyclerView.Adapter<PlaylistItemAdapter.PlaylistItemViewHolder>() {
    private var collect:List<Item> = emptyList()
//    val repository= Repository(context)
//    val vmf= PlaylistViewModelFactory(repository)
//    private var viewmodel:PlaylistViewModel = ViewModelProvider(ViewModelStoreOwner { ViewModelStore() },vmf).get(PlaylistViewModel::class.java)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.video_recview_item, parent, false)
        return PlaylistItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        val collect= collect[position]
        holder.itemtitle.text= collect?.snippet?.title
        holder.itemchannelname.text= collect?.snippet?.channelTitle.toString()
//        holder.item_duration.text= collect?.
        Picasso.with(context)
            .load(collect?.snippet?.thumbnails?.standard?.url)
            .into(holder.itemthumbnail)
//        viewmodel.getVideoinfo("content",collect?.snippet?.resourceId?.videoId.toString())
//        viewmodel.videoinfo.observe (LifecycleOwner { LifecycleOwner/**/ }, Observer {
//
//        })
//        holder.itemView.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data= Uri.parse(collect?.url.toString())
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
//        }
    }

    override fun getItemCount(): Int {
        return collect.size
    }

    class PlaylistItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemthumbnail=itemView.findViewById<ImageView>(R.id.video_img_iv)
        val itemtitle=itemView.findViewById<TextView>(R.id.video_title_iv)
        val itemchannelname=itemView.findViewById<TextView>(R.id.video_channel_name_iv)
//        val itemchanneldp = itemView.findViewById<ImageView>(R.id.video_channel_dp_iv)
//        val item_duration= itemView.findViewById<TextView>(R.id.video_duration_iv)


    }

    fun playlistitemsetStateWiseTracker(list: List<Item>){
        this.collect=list
        notifyDataSetChanged()
    }
}
