package com.dsckiet.youthhub.Adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.dsckiet.youthhub.Adapters.PlaylistItemAdapter.PlaylistItemViewHolder
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.ViewModel.PlaylistViewModel
import com.dsckiet.youthhub.ViewModel.PlaylistViewModelFactory
import com.dsckiet.youthhub.model.Item
import com.example.youthhub.Repo.Repository
import com.squareup.picasso.Picasso
import java.security.acl.Owner

class PlaylistItemAdapter() : Adapter<PlaylistItemViewHolder>(), Parcelable {
    private lateinit var collecty:List<Item>
    private lateinit var context : Context
    constructor(context: Context,collecty: List<Item>) : this() {
        this.context = context
        this.collecty = collecty
        Log.e("COLLECTY",collecty.toString())
    }

//    val repository= Repository(context)
//    val vmf= PlaylistViewModelFactory(repository)
//    private var viewmodel:PlaylistViewModel = ViewModelProvider(ViewModelStoreOwner { ViewModelStore() },vmf).get(PlaylistViewModel::class.java)

    constructor(parcel: Parcel) : this() {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistItemViewHolder {
        val v= LayoutInflater.from(parent.context).inflate(R.layout.video_recview_item, parent, false)
        return PlaylistItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: PlaylistItemViewHolder, position: Int) {
        val collect= collecty[position]
        holder.itemtitle.text= collect?.snippet?.title
        holder.itemchannelname.text= collect?.snippet?.channelTitle.toString()
//        holder.item_duration.text= collect?.
        Picasso.with(context)
            .load(collect?.snippet?.thumbnails?.standard?.url)
            .into(holder.itemthumbnail)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("playlistvidelist_videoid",collect?.snippet?.resourceId.videoId)
            bundle.putString("playlistvidelist_channel_name",collect?.snippet?.channelTitle)
            bundle.putString("playlistvidelist_video_title",collect?.snippet?.title)
            bundle.putString("playlistvidelist_description",collect?.snippet?.description)
            bundle.putString("playlistvidelist_published_at",collect?.snippet?.publishedAt)

            it.findNavController().navigate(R.id.action_playlistVideoListFragment_to_videoPlayerFragment,bundle)
        }
//        Log.e("VIDEOID",collect?.snippet?.resourceId?.videoId.toString())
//        viewmodel.getVideoinfo("contentDetails", collect?.snippet?.resourceId?.videoId.toString())
//        viewmodel.videoinfo.observeForever {
//            Log.e("POSITION", position.toString())
//            Log.e("DURATION",it?.body()?.items?.get(position)?.contentDetails?.duration.toString())
//
//            val str = it?.body()?.items?.get(0)?.contentDetails?.duration.toString()
//            var s:String=""
//            // str = PT5H8M19S
//            var len:Int = str.length
//            var flag=0
//            for(i in str){
//                if(i in '0'..'9'){
//                    s += i
//                }
//                else if( i == 'H' || i == 'M' || i == 'D'){
//                    s+=':'
//                }
//            }
//            Log.e("CONVERTED", s)
//            holder.item_duration.text = s
//        }

    }

    override fun getItemCount(): Int {
        return collecty.size
    }

    class PlaylistItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemthumbnail=itemView.findViewById<ImageView>(R.id.video_img_iv)
        val itemtitle=itemView.findViewById<TextView>(R.id.video_title_iv)
        val itemchannelname=itemView.findViewById<TextView>(R.id.video_channel_name_iv)
//        val itemchanneldp = itemView.findViewById<ImageView>(R.id.video_channel_dp_iv)
        val item_duration= itemView.findViewById<TextView>(R.id.video_duration_iv)


    }

    fun playlistitemsetStateWiseTracker(list: List<Item>){
        this.collecty=list
        notifyDataSetChanged()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlaylistItemAdapter> {
        override fun createFromParcel(parcel: Parcel): PlaylistItemAdapter {
            return PlaylistItemAdapter(parcel)
        }

        override fun newArray(size: Int): Array<PlaylistItemAdapter?> {
            return arrayOfNulls(size)
        }
    }


}
