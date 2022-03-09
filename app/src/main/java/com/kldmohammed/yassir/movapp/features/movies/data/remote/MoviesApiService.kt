package com.kldmohammed.yassir.movapp.features.movies.data.remote

import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MovieDto
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MoviesResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApiService {
    
    @GET("discover/movie-discover")
    fun fetchAllMovies(): Call<MoviesResponseDto>
    
    @GET("movie/{movie_id}")
    fun fetchMovieDetails(
        @Path("movie_id") movieId: Int,
    ): Call<MovieDto>
    
    
}