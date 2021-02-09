package com.recyclerviewmvvmdatabinding.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.recyclerviewmvvmdatabinding.R
import com.recyclerviewmvvmdatabinding.data.models.Movie
import com.recyclerviewmvvmdatabinding.databinding.RecyclerMovieBinding


class MoviesAdapter(val movie: List<Movie>, private val listener: RecycleViewClickListener) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.recycler_movie, parent, false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.recyclerMovieBinding.movie = movie[position]

        holder.recyclerMovieBinding.root.setOnClickListener { }

        holder.recyclerMovieBinding.movieImage.setOnClickListener {
            listener.onItemClick(holder.recyclerMovieBinding.movieImage, movie[position])
        }
        holder.recyclerMovieBinding.movieTextTitle.setOnClickListener {
            listener.onItemClick(holder.recyclerMovieBinding.movieTextTitle, movie[position])
        }
        holder.recyclerMovieBinding.movieTextBio.setOnClickListener {
            listener.onItemClick(holder.recyclerMovieBinding.movieTextBio, movie[position])
        }
    }

    override fun getItemCount() = movie.size


    class MovieViewHolder(val recyclerMovieBinding: RecyclerMovieBinding) :
        RecyclerView.ViewHolder(recyclerMovieBinding.root)

}

