package com.noblecilla.movies.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noblecilla.movies.databinding.LayoutFavoritesBinding
import com.noblecilla.movies.model.Movie
import com.noblecilla.movies.view.utils.posterLoading

class FavoriteAdapter(
    private var list: List<Movie>,
    private val listener: (Movie) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutFavoritesBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], listener)
    }

    override fun getItemCount() = list.size

    fun update(list: List<Movie>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: LayoutFavoritesBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
