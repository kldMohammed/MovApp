package com.kldmohammed.yassir.movapp.features.movies.data.datasource.impl

import com.kldmohammed.yassir.movapp.common.extensions.asUiState
import com.kldmohammed.yassir.movapp.features.movies.data.datasource.MoviesDataSource
import com.kldmohammed.yassir.movapp.features.movies.data.remote.MoviesApiService
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MovieDto
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MoviesResponseDto

class MoviesDataSourceImpl(private val apiService: MoviesApiService) : MoviesDataSource {
    override suspend fun loadAllMovies(): Result<MoviesResponseDto> {
        return apiService.fetchAllMovies().asUiState()
    }
    
    override suspend fun loadMovieDetails(movieId: Long): Result<MovieDto> {
        return apiService.fetchMovieDetails(movieId).asUiState()
    }
}