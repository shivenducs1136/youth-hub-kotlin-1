package com.example.youthhub.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.dsckiet.youthhub.R
import com.dsckiet.youthhub.databinding.FragmentVideoPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.PlayerUiController
import org.w3c.dom.Text

class VideoPlayerFragment : Fragment() {

    private lateinit var binding:FragmentVideoPlayerBinding
    private lateinit var videoID:String
    private lateinit var channel_name:String
    private  var video_title:String =""
    private lateinit var published_at:String
    private lateinit var description:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_video_player,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val youTubePlayerView = binding.youtubePlayerView
        getLifecycle().addObserver(youTubePlayerView)
        val bundle = arguments
        videoID = bundle!!.getString("playlistvidelist_videoid").toString()
        channel_name = bundle!!.getString("playlistvidelist_channel_name").toString()
        video_title = bundle.getString("playlistvidelist_video_title").toString()
        published_at = bundle!!.getString("playlistvidelist_published_at").toString()
        description = bundle!!.getString("playlistvidelist_description").toString()
        Log.e("VIDEO_ID_VIDEOPLAYER" , videoID)
        Log.e("VIDEO_TITLE_VIDEOPLAYER" , video_title)
        var d : TextView = view.findViewById(R.id.video_title)
        d.text = video_title
        binding.videoPlayerChannelName?.text = channel_name
//        binding.videoTitle?.text = video_title
//        Log.e("Video_title",binding.videoTitle?.text.toString())
        binding.videoPlayerDownArrow?.visibility = View.VISIBLE
        binding.videoPlayerUparrow?.visibility = View.GONE
        binding.videoPlayerDescriptionBox?.visibility = View.GONE
        binding.clicky?.setOnClickListener {

            if(binding.videoPlayerDownArrow!!.isVisible){
                binding.videoPlayerDownArrow?.visibility = View.GONE
                binding.videoPlayerUparrow?.visibility = View.VISIBLE
                binding.videoPlayerDescriptionBox?.visibility = View.VISIBLE
                binding.descriptionBox?.text = description
            }
            else{
                binding.videoPlayerDownArrow?.visibility = View.VISIBLE
                binding.videoPlayerUparrow?.visibility = View.GONE
                binding.videoPlayerDescriptionBox?.visibility = View.GONE
            }
        }
        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayerView.getPlayerUiController();
                val videoId = "${videoID}" //change according to your need
                youTubePlayer.play()
                youTubePlayer.cueVideo(videoId, 0F)
            }
        })
    }

}