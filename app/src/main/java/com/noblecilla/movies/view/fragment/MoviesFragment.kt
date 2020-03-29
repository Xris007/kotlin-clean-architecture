package com.noblecilla.movies.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.noblecilla.movies.databinding.FragmentMoviesBinding
import com.noblecilla.movies.model.Movie
import com.noblecilla.movies.view.adapter.MovieAdapter
import com.noblecilla.movies.view.utils.toast
import com.noblecilla.movies.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding
        get() = _binding!!

    private val movieViewModel: MovieViewModel by viewModel()

    private lateinit var nowPlayingLayoutManager: LinearLayoutManager
    private lateinit var popularLayoutManager: LinearLayoutManager
    private lateinit var topRatedLayoutManager: LinearLayoutManager
    private lateinit var upcomingLayoutManager: LinearLayoutManager

    private lateinit var nowPlayingAdapter: MovieAdapter
    private lateinit var popularAdapter: MovieAdapter
    private lateinit var topRatedAdapter: MovieAdapter
    private lateinit var upcomingAdapter: MovieAdapter

    private var nowPlayingPage = 1
    private var popularPage = 1
    private var topRatedPage = 1
    private var upcomingPage = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)

        setupViewModel()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupNowPlaying()
        setupPopular()
        setupTopRated()
        setupUpcoming()
    }

    private fun setupViewModel() {
        movieViewModel.nowPlaying.observe(viewLifecycleOwner, nowPlaying)
        movieViewModel.popular.observe(viewLifecycleOwner, popular)
        movieViewModel.topRated.observe(viewLifecycleOwner, topRated)
        movieViewModel.upcoming.observe(viewLifecycleOwner, upcoming)
        movieViewModel.onMessageError.observe(viewLifecycleOwner, onMessageError)
    }

    private val nowPlaying = Observer<List<Movie>> {
        nowPlayingAdapter.add(it)
        attachNowPlayingOnScrollListener()
    }

    private val popular = Observer<List<Movie>> {
        popularAdapter.add(it)
        attachPopularOnScrollListener()
    }

    private val topRated = Observer<List<Movie>> {
        topRatedAdapter.add(it)
        attachTopRatedOnScrollListener()
    }

    private val upcoming = Observer<List<Movie>> {
        upcomingAdapter.add(it)
        attachUpcomingOnScrollListener()
    }

    private val onMessageError = Observer<Any> {
        activity?.toast("$it")
    }

    private fun setupNowPlaying() {
        nowPlayingLayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        nowPlayingAdapter = MovieAdapter(mutableListOf()) {
            goToMovie(it)
        }

        binding.nowPlayingRecycler.apply {
            layoutManager = nowPlayingLayoutManager
            adapter = nowPlayingAdapter
        }
    }

    private fun setupPopular() {
        popularLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        popularAdapter = MovieAdapter(mutableListOf()) {
            goToMovie(it)
        }

        binding.popularRecycler.apply {
            layoutManager = popularLayoutManager
            adapter = popularAdapter
        }
    }

    private fun setupTopRated() {
        topRatedLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        topRatedAdapter = MovieAdapter(mutableListOf()) {
            goToMovie(it)
        }

        binding.topRatedRecycler.apply {
            layoutManager = topRatedLayoutManager
            adapter = topRatedAdapter
        }
    }

    private fun setupUpcoming() {
        upcomingLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        upcomingAdapter = MovieAdapter(mutableListOf()) {
            goToMovie(it)
        }

        binding.upcomingRecycler.apply {
            layoutManager = upcomingLayoutManager
            adapter = upcomingAdapter
        }
    }

    private fun attachNowPlayingOnScrollListener() {
        binding.nowPlayingRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = nowPlayingLayoutManager.itemCount
                val visibleItemCount = nowPlayingLayoutManager.childCount
                val firstVisibleItem = nowPlayingLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    recyclerView.removeOnScrollListener(this)
                    nowPlayingPage++
                    movieViewModel.nowPlaying(nowPlayingPage)
                }
            }
        })
    }

    private fun attachPopularOnScrollListener() {
        binding.popularRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularLayoutManager.itemCount
                val visibleItemCount = popularLayoutManager.childCount
                val firstVisibleItem = popularLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    recyclerView.removeOnScrollListener(this)
                    popularPage++
                    movieViewModel.popular(popularPage)
                }
            }
        })
    }

    private fun attachTopRatedOnScrollListener() {
        binding.topRatedRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedLayoutManager.itemCount
                val visibleItemCount = topRatedLayoutManager.childCount
                val firstVisibleItem = topRatedLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    recyclerView.removeOnScrollListener(this)
                    topRatedPage++
                    movieViewModel.topRated(topRatedPage)
                }
            }
        })
    }

    private fun attachUpcomingOnScrollListener() {
        binding.upcomingRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = upcomingLayoutManager.itemCount
                val visibleItemCount = upcomingLayoutManager.childCount
                val firstVisibleItem = upcomingLayoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    recyclerView.removeOnScrollListener(this)
                    upcomingPage++
                    movieViewModel.upcoming(upcomingPage)
                }
            }
        })
    }

    private fun goToMovie(movie: Movie) {
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToDetailActivity(
                movie
            )
        )
    }

    override fun onResume() {
        super.onResume()
        movieViewModel.nowPlaying(nowPlayingPage)
        movieViewModel.popular(popularPage)
        movieViewModel.topRated(topRatedPage)
        movieViewModel.upcoming(upcomingPage)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
