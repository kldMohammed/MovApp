package com.kldmohammed.yassir.movapp.features.movies.ui.all

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kldmohammed.yassir.movapp.databinding.MovieListItemBinding
import com.kldmohammed.yassir.movapp.features.movies.domain.model.Movie


class MoviesListAdapter() :
    ListAdapter<Movie, MoviesListAdapter.ViewHolder>(PharmacyDiffCallback()) {
    
    var onMovieClicked: ((Movie) -> Unit) = { movie: Movie -> }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieListItemBinding
            .inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }
    
    
    inner class ViewHolder constructor(val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        
        fun bind(movie: Movie) {
            binding.movieTitleTxt.text = movie.title
            binding.releaseDateTxt.text = movie.releaseDate
            
            binding.movieImage.load("https://image.tmdb.org/t/p/original/${movie.posterPath}")
            binding.root.setOnClickListener {
                onMovieClicked.invoke(movie)
            }
        }
        
    }
}


class PharmacyDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
        
    }
}




