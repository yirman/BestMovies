package com.toolbox.bestmovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.toolbox.bestmovies.R
import com.toolbox.bestmovies.data.entities.Carousel
import com.toolbox.bestmovies.data.entities.Movie
import com.toolbox.bestmovies.data.local.AppDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movie1 = Movie(1, 2, "Mi culito de Oro")
        val movie2 = Movie(1, 3, "Mi culito de Oro parte 2")
        val movies = ArrayList<Movie>()
        movies.apply {
            add(movie1)
            add(movie2)
        }
        val carousel = Carousel(1, "Carousel", "", movies)

        val carouselDao = AppDatabase.getDatabase(this).carouselDao()
        carouselDao.queryCarousels().observe(this, Observer {

            it

        })
        carouselDao.queryMovies().observe(this, Observer {

            it

        })

//        carouselDao.insertCarouselWithMovies(carousel)
//
//        carouselDao.deleteAllCarousels()
    }
}