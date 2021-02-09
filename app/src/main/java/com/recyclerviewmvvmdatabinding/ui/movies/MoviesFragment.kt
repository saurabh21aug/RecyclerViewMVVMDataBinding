package com.recyclerviewmvvmdatabinding.ui.movies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.recyclerviewmvvmdatabinding.R
import com.recyclerviewmvvmdatabinding.data.models.Movie
import com.recyclerviewmvvmdatabinding.data.network.MoviesApi
import com.recyclerviewmvvmdatabinding.data.repository.MoviesRepository
import com.recyclerviewmvvmdatabinding.databinding.MoviesFragmentBinding
import com.recyclerviewmvvmdatabinding.util.hide
import com.recyclerviewmvvmdatabinding.util.show
import com.recyclerviewmvvmdatabinding.util.toast

class MoviesFragment : Fragment(), RecycleViewClickListener {


    private lateinit var viewModel: MoviesViewModel

    private var _binding: MoviesFragmentBinding? = null
    private val binding
        get() = _binding!!

    private lateinit var factory: MoviesViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = MoviesApi()
        val repository = MoviesRepository(api)
        factory = MoviesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)

        viewModel.getMovies()

        binding.progressbar.show()
        viewModel.movie.observe(viewLifecycleOwner, { movies ->
            binding.progressbar.hide()
            binding.recyclerViewMovie.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = MoviesAdapter(movies, this@MoviesFragment)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onItemClick(view: View, movie: Movie) {
        when (view.id) {
            R.id.movie_image -> context.toast("Image Click")
            R.id.movie_text_title -> context.toast("Title click")
            R.id.movie_text_bio -> context.toast("Bio click")
        }
    }
}