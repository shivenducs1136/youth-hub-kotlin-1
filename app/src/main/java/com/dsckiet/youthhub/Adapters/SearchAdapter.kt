package com.example.youthhub.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.model.Item
import com.squareup.picasso.Picasso

class SearchAdapter  {}//    private var collectid:List<Item> = emptyList()
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.youthhub.Adapters.SearchAdapter.SearchViewHolder {
//        val v= LayoutInflater.from(parent.context).inflate( R.layout.video_recview_item, parent, false)
//        return com.example.youthhub.Adapters.SearchAdapter.SearchViewHolder(v)
//    }
//
//    override fun onBindViewHolder(holder: com.example.youthhub.Adapters.SearchAdapter.SearchViewHolder, position: Int) {
//        val collectid= collectid[position]
//////        if(collectid?.country_id == 1){
////
////        holder?.title.text = collectid
////        Picasso.with(context)
////            .load(collectid?.thumbnails?.default?.url)
////            .into(holder.channel_dp)
////        holder.itemView.setOnClickListener {
//
//
//            Toast.makeText(context,"Clicked ",Toast.LENGTH_SHORT).show()
////            var bundle = Bundle()
////            bundle.putString("id1",collectid?.id.toString())
////            it.findNavController().navigate(R.id.action_mapFragment2_to_serachedCityResultsFragment,bundle)
//        }
////        }
////        else{
////            holder.itemView.visibility = View.GONE
////        }
//
//    }
//
//
//
//
//
//    override fun getItemCount(): Int {
//        return collectid.size
//    }
//
//    fun citysetStateWiseTracker(list: List<Item.Snippet>){
//        this.collectid=list
//        notifyDataSetChanged()
//    }
//
//
//    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        val title = itemView.findViewById<TextView>(R.id.video_title_iv)
//        val thumbnail = itemView.findViewById<ImageView>(R.id.video_img_iv)
//        val channelname = itemView.findViewById<TextView>(R.id.video_channel_name_iv)
//        val channel_dp = itemView.findViewById<ImageView>(R.id.video_channel_dp_iv)
//        val duration = itemView.findViewById<TextView>(R.id.video_duration_iv)
//    }
//
//
//
//}
//
