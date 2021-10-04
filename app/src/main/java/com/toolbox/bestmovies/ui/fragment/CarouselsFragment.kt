package com.toolbox.bestmovies.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.toolbox.bestmovies.R
import com.toolbox.bestmovies.data.remote.Resource
import com.toolbox.bestmovies.databinding.FragmentCarouselsBinding
import com.toolbox.bestmovies.ui.adapters.CarouselAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarouselsFragment : Fragment(), CarouselAdapter.MovieHandler {

    private var _binding: FragmentCarouselsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CarouselViewModel by viewModels()
    private lateinit var adapter: CarouselAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarouselsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObserver()
    }

    private fun setupRecyclerView() {
        adapter = CarouselAdapter(this)
        binding.rvCarousels.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCarousels.adapter = adapter
    }

    private fun setupObserver(){
        viewModel.carousels.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClickMovie(idMovie: Int) {
        findNavController().navigate(
            R.id.action_carouselsFragment_to_movieInfoFragment,
            bundleOf("id" to idMovie)
        )
    }

}