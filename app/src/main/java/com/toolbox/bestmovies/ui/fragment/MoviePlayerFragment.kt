package com.toolbox.bestmovies.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.toolbox.bestmovies.databinding.FragmentMoviePlayerBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoviePlayerFragment : Fragment() {

    private var _binding: FragmentMoviePlayerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    private var simpleExoPlayer: SimpleExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviePlayerBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let {
            viewModel.queryMovie(it)
        }
        setupObservers()
    }
    private fun setupObservers(){
        viewModel.movie.observe(viewLifecycleOwner, { movie ->
            movie.videoUrl?.let{ videoUrl ->
                simpleExoPlayer = SimpleExoPlayer.Builder(binding.root.context).build()
                binding.playervideo.player = simpleExoPlayer
                val mediaItem = MediaItem.fromUri(videoUrl)
                simpleExoPlayer?.addMediaItem(mediaItem)
                simpleExoPlayer?.prepare();
                simpleExoPlayer?.playWhenReady = true;
            }
        })
    }

    override fun onResume() {
        super.onResume()
        simpleExoPlayer?.playWhenReady = true
    }

    override fun onPause() {
        simpleExoPlayer?.playWhenReady = false
        super.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.playervideo?.player = null
        simpleExoPlayer?.release()
        simpleExoPlayer = null
        _binding = null
    }

}