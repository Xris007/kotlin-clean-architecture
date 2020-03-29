package com.noblecilla.movies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noblecilla.domain.interactor.MovieInteractor
import com.noblecilla.domain.model.MovieDomain
import com.noblecilla.domain.vo.Result
import com.noblecilla.movies.mapper.MovieMapper
import com.noblecilla.movies.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val interactor: MovieInteractor) : ViewModel() {

    private val _nowPlaying = MutableLiveData<List<Movie>>()
    val nowPlaying: LiveData<List<Movie>> = _nowPlaying

    private val _popular = MutableLiveData<List<Movie>>()
    val popular: LiveData<List<Movie>> = _popular

    private val _topRated = MutableLiveData<List<Movie>>()
    val topRated: LiveData<List<Movie>> = _topRated

    private val _upcoming = MutableLiveData<List<Movie>>()
    val upcoming: LiveData<List<Movie>> = _upcoming

    private val _favorites = MutableLiveData<List<Movie>>()
    val favorites: LiveData<List<Movie>> = _favorites

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    private val _favoriteError = MutableLiveData<Any>()
    val favoriteError: LiveData<Any> = _favoriteError

    private val _insert = MutableLiveData<Boolean>()
    val insert: LiveData<Boolean> = _insert

    private val _delete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean> = _delete

    private val _onMessageError = MutableLiveData<Any>()
    val onMessageError: LiveData<Any> = _onMessageError

    fun nowPlaying(page: Int) = viewModelScope.launch {
        val result: Result<List<MovieDomain>> = withContext(Dispatchers.IO) {
            interactor.nowPlayingRest(page)
        }

        when (result) {
            is Result.Success -> _nowPlaying.value = MovieMapper.mapFromListEntity(result.data)
            is Result.Error -> _onMessageError.value = result.exception
        }
    }

    fun popular(page: Int) = viewModelScope.launch {
        val result: Result<List<MovieDomain>> = withContext(Dispatchers.IO) {
            interactor.popularRest(page)
        }

        when (result) {
            is Result.Success -> _popular.value = MovieMapper.mapFromListEntity(result.data)
            is Result.Error -> _onMessageError.value = result.exception
        }
    }

    fun topRated(page: Int) = viewModelScope.launch {
        val result: Result<List<MovieDomain>> = withContext(Dispatchers.IO) {
            interactor.topRatedRest(page)
        }

        when (result) {
            is Result.Success -> _topRated.value = MovieMapper.mapFromListEntity(result.data)
            is Result.Error -> _onMessageError.value = result.exception
        }
    }

    fun upcoming(page: Int) = viewModelScope.launch {
        val result: Result<List<MovieDomain>> = withContext(Dispatchers.IO) {
            interactor.upcomingRest(page)
        }

        when (result) {
            is Result.Success -> _upcoming.value = MovieMapper.mapFromListEntity(result.data)
            is Result.Error -> _onMessageError.value = result.exception
        }
    }

    fun favorites() = viewModelScope.launch {
        val result: Result<List<MovieDomain>> = withContext(Dispatchers.IO) {
            interactor.favoritesDb()
        }

        when (result) {
            is Result.Success -> _favorites.value = MovieMapper.mapFromListEntity(result.data)
            is Result.Error -> _onMessageError.value = result.exception
        }
    }

    fun favorite(movie: Movie) = viewModelScope.launch {
        val result: Result<Boolean> = withContext(Dispatchers.IO) {
            interactor.favoriteDb(MovieMapper.mapToEntity(movie))
        }

        when (result) {
            is Result.Success -> _favorite.value = result.data
            is Result.Error -> _favoriteError.value = result.exception
        }
    }

    fun insert(movie: Movie) = viewModelScope.launch {
        val result: Result<Boolean> = withContext(Dispatchers.IO) {
            interactor.insertFavoriteDb(MovieMapper.mapToEntity(movie))
        }

        when (result) {
            is Result.Success -> _insert.value = result.data
            is Result.Error -> _onMessageError.value = result.exception
        }
    }

    fun delete(movie: Movie) = viewModelScope.launch {
        val result: Result<Boolean> = withContext(Dispatchers.IO) {
            interactor.deleteFavoriteDb(MovieMapper.mapToEntity(movie))
        }

        when (result) {
            is Result.Success -> _delete.value = result.data
            is Result.Error -> _onMessageError.value = result.exception
        }
    }
}
