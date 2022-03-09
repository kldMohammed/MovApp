package com.kldmohammed.yassir.movapp.features.movies.data.datasource

import com.kldmohammed.androidtechtask.common.UiState
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MovieDto
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MoviesResponseDto

interface MoviesDataSource {
    
    suspend fun loadAllMovies(): UiState<MoviesResponseDto>
    
    suspend fun loadMovieDetails(movieId: Long): UiState<MovieDto>
}