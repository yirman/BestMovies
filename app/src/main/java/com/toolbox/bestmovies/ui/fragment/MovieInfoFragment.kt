package com.toolbox.bestmovies.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.toolbox.bestmovies.R
import com.toolbox.bestmovies.databinding.FragmentMovieInfoBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule


@AndroidEntryPoint
class MovieInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMovieInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    private var idMovie: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieInfoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idMovie = arguments?.getInt("id")?.apply {
            viewModel.queryMovie(this)
        }
        setupObservers()
        binding.btnTrailer.setOnClickListener {
            findNavController().navigate(
                R.id.action_movieInfoFragment_to_moviePlayerFragment,
                bundleOf("id" to idMovie)
            )
        }
    }
    private fun setupObservers(){
        viewModel.movie.observe(viewLifecycleOwner, { movie ->
            Glide.with(binding.root)
                .load(movie.imageUrl)
                .placeholder(CircularProgressDrawable(binding.root.context).apply { start() })
                .signature(ObjectKey(movie))
                .into(binding.ivImage.ivMovie)

            binding.tvTitle.text = movie.title
            binding.tvDescription.text = movie.description

            movie.videoUrl?.let {
                binding.viewFlipper.showNext()
            }

        })
    }
}