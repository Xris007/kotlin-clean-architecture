package com.noblecilla.movies.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noblecilla.movies.databinding.LayoutMoviesBinding
import com.noblecilla.movies.model.Movie
import com.noblecilla.movies.view.utils.posterLoading

class MovieAdapter(
    private val list: MutableList<Movie>,
    private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutMoviesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount() = list.size

    fun add(list: List<Movie>) {
        this.list.addAll(list)
        notifyItemRangeInserted(
            this.list.size, list.size - 1
        )
    }

    class ViewHolder(private val binding: LayoutMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie, listener: (Movie) -> Unit) =
            with(itemView) {
                binding.moviePoster.posterLoading(
                    "https://image.tmdb.org/t/p/w342${movie.posterPath}",
                    binding.progress
                )

                setOnClickListener {
                    listener(movie)
                }
            }
    }
}
