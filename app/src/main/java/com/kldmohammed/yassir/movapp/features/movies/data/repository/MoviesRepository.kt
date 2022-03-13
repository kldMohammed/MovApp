package com.kldmohammed.yassir.movapp.features.movies.data.repository

import com.kldmohammed.yassir.movapp.features.movies.domain.model.Movie
import com.kldmohammed.yassir.movapp.features.movies.domain.model.MovieDetails

interface MoviesRepository {
    
    suspend fun loadAllMovies(): Result<List<Movie>>
    
    suspend fun loadMovieDetails(movieId: Long): Result<MovieDetails>
    
}