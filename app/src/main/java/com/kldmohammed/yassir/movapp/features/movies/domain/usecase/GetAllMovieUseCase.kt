package com.kldmohammed.yassir.movapp.features.movies.domain.usecase

import com.kldmohammed.yassir.movapp.features.movies.data.repository.MoviesRepository
import com.kldmohammed.yassir.movapp.features.movies.domain.model.Movie


class GetAllMovieUseCase(
    private val moviesRepository: MoviesRepository,
) {
    suspend operator fun invoke(): Result<List<Movie>> {
        return moviesRepository.loadAllMovies()
    }
}