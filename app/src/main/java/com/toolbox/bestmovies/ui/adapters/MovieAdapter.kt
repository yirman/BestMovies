package com.toolbox.bestmovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.signature.ObjectKey
import com.toolbox.bestmovies.R
import com.toolbox.bestmovies.data.entities.Carousel
import com.toolbox.bestmovies.data.entities.Movie
import com.toolbox.bestmovies.databinding.ItemMovieBinding

class MovieAdapter (private val carouselType: String?): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val items = mutableListOf<Movie>()

    fun setItems(items: List<Movie>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when(carouselType){
            Carousel.TYPE_THUMB -> ITEM_THUMB
            Carousel.TYPE_POSTER -> ITEM_POSTER
            else -> ITEM_POSTER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        var imageViewLayout = when(viewType){
            ITEM_THUMB -> R.layout.thumb_image
            ITEM_POSTER -> R.layout.poster_image
            else -> R.layout.poster_image
        }
        val imageView = LayoutInflater.from(parent.context)
            .inflate(imageViewLayout, parent, false)
        binding.imageViewContainer.addView(imageView)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    class MovieViewHolder(private val itemBinding: ItemMovieBinding): RecyclerView.ViewHolder(itemBinding.root){

        fun bind (movie: Movie){
            itemBinding.tvTitle.text = movie.title
            Glide.with(itemBinding.root)
                .load(movie.imageUrl)
                .signature(ObjectKey(movie))
                .into(itemBinding.imageViewContainer.findViewById(R.id.iv_movie))
        }
    }

    companion object {
        private const val ITEM_THUMB = 0
        private const val ITEM_POSTER = 1
    }
}