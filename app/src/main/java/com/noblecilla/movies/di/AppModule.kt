package com.noblecilla.movies.di

import com.noblecilla.data.datasource.MovieDataStoreFactory
import com.noblecilla.data.repository.MovieDataRepository
import com.noblecilla.domain.interactor.MovieInteractor
import com.noblecilla.domain.repository.MovieRepository
import com.noblecilla.movies.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { MovieDataStoreFactory(androidContext()) }
    single<MovieRepository> { MovieDataRepository(get()) }
    single { MovieInteractor(get()) }
    viewModel { MovieViewModel(get()) }
}
