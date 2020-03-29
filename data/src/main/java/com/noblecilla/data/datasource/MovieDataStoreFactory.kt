package com.noblecilla.data.datasource

import android.content.Context
import com.noblecilla.data.datasource.db.MovieDbDataStore
import com.noblecilla.data.datasource.rest.MovieRestDataStore

class MovieDataStoreFactory(private val context: Context) {

    companion object {
        const val DB = 1
        const val REST = 2
    }

    fun create(dataSource: Int): MovieDataStore? {
        var dataStore: MovieDataStore? = null

        when (dataSource) {
            DB -> dataStore = createDbDataStore()
            REST -> dataStore = createRestDataStore()
        }

        return dataStore
    }

    private fun createDbDataStore(): MovieDataStore {
        return MovieDbDataStore(context)
    }

    private fun createRestDataStore(): MovieDataStore {
        return MovieRestDataStore()
    }
}
