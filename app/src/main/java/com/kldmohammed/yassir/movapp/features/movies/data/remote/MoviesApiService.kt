package com.kldmohammed.yassir.movapp.features.movies.data.remote

import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MovieDto
import com.kldmohammed.yassir.movapp.features.movies.data.remote.model.MoviesResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {
    
    @GET("discover/movie")
    fun fetchAllMovies(
        @Query("api_key") apiKey :String= "92eac0205b154b45d0d35306e2e62933"
    ): Call<MoviesResponseDto>
    
    @GET("movie/{movie_id}")
    fun fetchMovieDetails(
        @Path("movie_id") movieId: Long,
    ): Call<MovieDto>
    
    
}