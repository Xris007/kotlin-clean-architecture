package com.noblecilla.movies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.noblecilla.movies.R
import com.noblecilla.movies.databinding.ActivityDetailBinding
import com.noblecilla.movies.model.Movie
import com.noblecilla.movies.view.utils.detailLoading
import com.noblecilla.movies.view.utils.toast
import com.noblecilla.movies.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val args: DetailActivityArgs by navArgs()
    private lateinit var movie: Movie

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        args.movie.let {
            title = it.title
            movie = it
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupViewModel()
        setupView()
    }

    private fun setupViewModel() {
        movieViewModel.favorite.observe(this, favorite)
        movieViewModel.favoriteError.observe(this, favoriteError)
        movieViewModel.insert.observe(this, insert)
        movieViewModel.delete.observe(this, delete)
        movieViewModel.onMessageError.observe(this, onMessageError)
    }

    private val favorite = Observer<Boolean> {
        binding.addFavorite.visibility = View.GONE
        binding.deleteFavorite.visibility = View.VISIBLE
    }

    private val favoriteError = Observer<Any> {
        binding.deleteFavorite.visibility = View.GONE
        binding.addFavorite.visibility = View.VISIBLE
    }

    private val insert = Observer<Boolean> {
        toast("Added to favorites")
        movieViewModel.favorite(movie)
    }

    private val delete = Observer<Boolean> {
        toast("Deleted from favorites")
        movieViewModel.favorite(movie)
    }

    private val onMessageError = Observer<Any> {
        toast("$it")
    }

    private fun setupView() {
        binding.movieBackdrop.detailLoading("https://image.tmdb.org/t/p/w1280${movie.backdropPath}")
        binding.moviePoster.detailLoading("https://image.tmdb.org/t/p/w342${movie.posterPath}")
        binding.movieTitle.text = movie.title
        binding.movieRating.rating = movie.voteAverage?.toFloat()?.div(2)!!
        binding.movieReleaseDate.text = movie.releaseDate
        binding.movieOverview.text = movie.overview

        binding.addFavorite.setOnClickListener {
            movieViewModel.insert(movie)
        }

        binding.deleteFavorite.setOnClickListener {
            movieViewModel.delete(movie)
        }
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.favorite(movie)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}
