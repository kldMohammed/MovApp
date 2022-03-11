package com.kldmohammed.yassir.movapp.features.movies.domain.usecase

import com.kldmohammed.yassir.movapp.features.movies.data.repository.MoviesRepository
import com.kldmohammed.yassir.movapp.features.movies.domain.model.MovieDetails


class GetMovieDetailsUseCase(
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(movieId: Long): Result<MovieDetails> {
        return moviesRepository.loadMovieDetails(movieId)
    }
}