package com.recyclerviewmvvmdatabinding.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recyclerviewmvvmdatabinding.util.Coroutine
import com.recyclerviewmvvmdatabinding.data.models.Movie
import com.recyclerviewmvvmdatabinding.data.repository.MoviesRepository
import kotlinx.coroutines.Job

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    private lateinit var job: Job

    private val _movies = MutableLiveData<List<Movie>>()

    val movie: LiveData<List<Movie>>
        get() = _movies

    fun getMovies() {
        job = Coroutine.ioThanMain(
            { repository.getMovies() },
            {
                _movies.value = it
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}