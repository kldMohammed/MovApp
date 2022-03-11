package com.kldmohammed.yassir.movapp.features.movies.data.repository.impl

import com.kldmohammed.yassir.movapp.features.movies.data.datasource.MoviesDataSource
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.toMovie
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.toMovieDetails
import com.kldmohammed.yassir.movapp.features.movies.data.repository.MoviesRepository
import com.kldmohammed.yassir.movapp.features.movies.domain.model.Movie
import com.kldmohammed.yassir.movapp.features.movies.domain.model.MovieDetails

class MoviesRepositoryImpl(private val moviesDataSource: MoviesDataSource) : MoviesRepository {
    
    override suspend fun loadAllMovies(): Result<List<Movie>> {
        val result = moviesDataSource.loadAllMovies()
        return result.map { movieResponse ->
            movieResponse.results.map { it.toMovie() }
        }
    }
    
    override suspend fun loadMovieDetails(movieId: Long): Result<MovieDetails> {
        return moviesDataSource.loadMovieDetails(movieId).map { it.toMovieDetails() }
    }
}