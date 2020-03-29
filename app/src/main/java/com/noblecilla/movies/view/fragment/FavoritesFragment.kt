package com.noblecilla.movies.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.noblecilla.movies.databinding.FragmentFavoritesBinding
import com.noblecilla.movies.model.Movie
import com.noblecilla.movies.view.adapter.FavoriteAdapter
import com.noblecilla.movies.view.utils.toast
import com.noblecilla.movies.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModel()

    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        setupViewModel()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupView()
    }

    private fun setupViewModel() {
        movieViewModel.favorites.observe(viewLifecycleOwner, favorites)
        movieViewModel.onMessageError.observe(viewLifecycleOwner, onMessageError)
    }

    private val favorites = Observer<List<Movie>> {
        favoriteAdapter.update(it)
    }

    private val onMessageError = Observer<Any> {
        activity?.toast("$it")
    }

    private fun setupView() {
        favoriteAdapter = FavoriteAdapter(emptyList()) {
            goToMovie(it)
        }

        activity?.let {
            binding.favoritesRecycler.apply {
                layoutManager = GridLayoutManager(it, 2)
                adapter = favoriteAdapter
            }
        }
    }

    private fun goToMovie(movie: Movie) {
        findNavController().navigate(
            FavoritesFragmentDirections.actionFavoritesFragmentToDetailActivity(
                movie
            )
        )
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.favorites()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
