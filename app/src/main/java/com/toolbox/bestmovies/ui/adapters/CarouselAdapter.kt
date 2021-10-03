package com.toolbox.bestmovies.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.toolbox.bestmovies.data.entities.Carousel
import com.toolbox.bestmovies.data.entities.CarouselWithMovies
import com.toolbox.bestmovies.databinding.ItemCarouselBinding

class CarouselAdapter () : RecyclerView.Adapter<CarouselAdapter.CarouselViewHolder>() {


    private val items = mutableListOf<CarouselWithMovies>()

    fun setItems(items: List<CarouselWithMovies>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding: ItemCarouselBinding = ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) = holder.bind(items[position].carousel)

    override fun getItemCount(): Int = items.size

    class CarouselViewHolder(private val itemBinding: ItemCarouselBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(carousel: Carousel) {
            itemBinding.tvTitle.text = carousel.title
        }

    }
}