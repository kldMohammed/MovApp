package com.kldmohammed.yassir.movapp.features.movies.data.datasource.impl

import com.kldmohammed.androidtechtask.common.UiState
import com.kldmohammed.yassir.movapp.common.extensions.asUiState
import com.kldmohammed.yassir.movapp.features.movies.data.datasource.MoviesDataSource
import com.kldmohammed.yassir.movapp.features.movies.data.remote.MoviesApiService
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MovieDto
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MoviesResponseDto

class MoviesDataSourceImpl(private val apiService: MoviesApiService) : MoviesDataSource {
    override suspend fun loadAllMovies(): UiState<MoviesResponseDto> {
        return apiService.fetchAllMovies().asUiState()
    }
    
    override suspend fun loadMovieDetails(movieId: Long): UiState<MovieDto> {
        return apiService.fetchMovieDetails(movieId).asUiState()
    }
}