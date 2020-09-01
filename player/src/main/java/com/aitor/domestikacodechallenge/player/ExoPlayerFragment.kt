package com.aitor.domestikacodechallenge.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aitor.domestikacodechallenge.player.databinding.FragmentExoPlayerBinding
import com.aitor.domestikacodechallenge.player.model.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class ExoPlayerFragment private constructor() : Fragment() {

    private lateinit var binding: FragmentExoPlayerBinding
    private lateinit var player: Player
    private lateinit var mediaItem: MediaItem

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExoPlayerBinding.inflate(inflater, container, false)
        val args = requireArguments()
        mediaItem = args.getParcelable(MEDIA_ITEM_KEY)!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player = createPlayer()
        binding.exoplayerView.player = player
    }

    override fun onPause() {
        super.onPause()
        player.playWhenReady = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player.release()
    }

    private fun createPlayer(): Player {
        return SimpleExoPlayer.Builder(requireContext()).build().apply {
            prepare(createMediaSource())
            playWhenReady = true
            volume = 1.0f
        }
    }

    private fun createMediaSource(): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            requireContext(),
            Util.getUserAgent(requireContext(), "domestika-code-challenge")
        )

        return ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(mediaItem.uri)
    }

    companion object {
        private const val MEDIA_ITEM_KEY = "media_item_key"

        fun newInstance(mediaItem: MediaItem): ExoPlayerFragment {
            val args = Bundle().apply {
                putParcelable(MEDIA_ITEM_KEY, mediaItem)
            }

            val fragment = ExoPlayerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}