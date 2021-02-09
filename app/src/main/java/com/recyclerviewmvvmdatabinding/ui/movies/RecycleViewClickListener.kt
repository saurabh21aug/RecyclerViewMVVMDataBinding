package com.recyclerviewmvvmdatabinding.ui.movies

import android.view.View
import com.recyclerviewmvvmdatabinding.data.models.Movie

interface RecycleViewClickListener {

    fun onItemClick(view: View, movie: Movie)
}