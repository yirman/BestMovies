package com.toolbox.bestmovies.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.toolbox.bestmovies.R
import com.toolbox.bestmovies.databinding.FragmentMovieInfoBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieInfoFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentMovieInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModels()

    private var idMovie: Int? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        bottomSheetDialog.setOnShowListener { dialog ->
            val dialog1 = dialog as BottomSheetDialog
            val bottomSheet: FrameLayout = dialog1.findViewById(com.google.android.material.R.id.design_bottom_sheet)!!
            BottomSheetBehavior.from(bottomSheet).state = BottomSheetBehavior.STATE_EXPANDED
            BottomSheetBehavior.from(bottomSheet).skipCollapsed = true
            BottomSheetBehavior.from(bottomSheet).isHideable = true

            val layoutParams = bottomSheet.layoutParams
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            bottomSheet.layoutParams = layoutParams
        }
        return bottomSheetDialog
    }

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