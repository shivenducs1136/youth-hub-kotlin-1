package com.example.youthhub.Adapters

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.R
import com.example.youthhub.searchapi.Snippet
import com.squareup.picasso.Picasso

class SearchAdapter (private val context: Context): RecyclerView.Adapter<com.example.youthhub.Adapters.SearchAdapter.SearchViewHolder>() {
    private var collectid:List<Snippet> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.youthhub.Adapters.SearchAdapter.SearchViewHolder {
        val v= LayoutInflater.from(parent.context).inflate( R.layout.video_recview_item, parent, false)
        return com.example.youthhub.Adapters.SearchAdapter.SearchViewHolder(v)
    }

    override fun onBindViewHolder(holder: com.example.youthhub.Adapters.SearchAdapter.SearchViewHolder, position: Int) {
        val collectid= collectid[position]
//        if(collectid?.country_id == 1){

        holder?.title.text = collectid.title
        Picasso.with(context)
            .load(collectid?.thumbnails?.default?.url)
            .into(holder.channel_dp)
        holder.itemView.setOnClickListener {


            Toast.makeText(context,"Clicked ",Toast.LENGTH_SHORT).show()
//            var bundle = Bundle()
//            bundle.putString("id1",collectid?.id.toString())
//            it.findNavController().navigate(R.id.action_mapFragment2_to_serachedCityResultsFragment,bundle)
        }
//        }
//        else{
//            holder.itemView.visibility = View.GONE
//        }

    }





    override fun getItemCount(): Int {
        return collectid.size
    }

    fun citysetStateWiseTracker(list: List<Snippet>){
        this.collectid=list
        notifyDataSetChanged()
    }


    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.video_title_iv)
        val thumbnail = itemView.findViewById<ImageView>(R.id.video_img_iv)
        val channelname = itemView.findViewById<TextView>(R.id.video_channel_name_iv)
        val channel_dp = itemView.findViewById<ImageView>(R.id.video_channel_dp_iv)
        val duration = itemView.findViewById<TextView>(R.id.video_duration_iv)
    }



}

