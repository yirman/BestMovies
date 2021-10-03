package com.toolbox.bestmovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.toolbox.bestmovies.data.entities.CarouselWithMovies
import com.toolbox.bestmovies.databinding.ItemCarouselBinding

class CarouselAdapter (private val handler: MovieHandler) : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>(), MovieAdapter.OnMovieClickListener {


    private val items = mutableListOf<CarouselWithMovies>()

    fun setItems(items: List<CarouselWithMovies>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding: ItemCarouselBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding, this)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    override fun onClickMovie(id: Int) {
        handler.onClickMovie(id)
    }


    class CarouselViewHolder(private val itemBinding: ItemCarouselBinding, private val listener: MovieAdapter.OnMovieClickListener) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: CarouselWithMovies) {
            itemBinding.tvTitle.text = item.carousel.title
            val movieAdapter = MovieAdapter(item.carousel.type, listener)
            movieAdapter.setItems(item.movies)
            itemBinding.rvMovies.layoutManager = LinearLayoutManager(itemBinding.root.context, RecyclerView.HORIZONTAL, false)
            itemBinding.rvMovies.adapter = movieAdapter
        }

    }

    interface MovieHandler{
        fun onClickMovie(idMovie: Int)
    }

}