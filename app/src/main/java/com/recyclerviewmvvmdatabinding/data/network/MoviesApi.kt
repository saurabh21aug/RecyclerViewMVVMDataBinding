package com.recyclerviewmvvmdatabinding.data.network

import com.recyclerviewmvvmdatabinding.data.models.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MoviesApi {

    @GET("marvel")
    suspend fun getMovies(): Response<List<Movie>>

    companion object {

        operator fun invoke(): MoviesApi {
            return Retrofit.Builder()
                .baseUrl("https://www.simplifiedcoding.net/demos/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }
}