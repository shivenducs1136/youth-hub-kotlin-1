package com.example.youthhub.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.R
import android.content.ClipData
import android.widget.ImageView

import androidx.annotation.NonNull

import android.widget.TextView




class ViewPagerAdapter (
    val images : List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>()
    {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(com.example.youthhub.R.layout.item_view_pager,parent,false)
            return ViewPagerViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
            val curImage = images[position]
            holder.itemView.findViewById<ImageView>(com.example.youthhub.R.id.ivImage).setImageResource(curImage)
        }

        override fun getItemCount(): Int {
            return images.size
        }

    }