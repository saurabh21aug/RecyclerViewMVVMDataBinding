package com.recyclerviewmvvmdatabinding.data.repository

import com.recyclerviewmvvmdatabinding.data.models.Movie
import com.recyclerviewmvvmdatabinding.data.SafeApiRequest
import com.recyclerviewmvvmdatabinding.data.network.MoviesApi

class MoviesRepository(private val api: MoviesApi) : SafeApiRequest() {

    suspend fun getMovies(): List<Movie> {
        return apiRequest { api.getMovies() }
    }
}