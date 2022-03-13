package com.kldmohammed.yassir.movapp.common.di

import com.kldmohammed.yassir.movapp.common.BASE_API_URL
import com.kldmohammed.yassir.movapp.features.movies.data.datasource.MoviesDataSource
import com.kldmohammed.yassir.movapp.features.movies.data.datasource.impl.MoviesDataSourceImpl
import com.kldmohammed.yassir.movapp.features.movies.data.remote.MoviesApiService
import com.kldmohammed.yassir.movapp.features.movies.data.repository.MoviesRepository
import com.kldmohammed.yassir.movapp.features.movies.data.repository.impl.MoviesRepositoryImpl
import com.kldmohammed.yassir.movapp.features.movies.domain.usecase.GetAllMovieUseCase
import com.kldmohammed.yassir.movapp.features.movies.domain.usecase.GetMovieDetailsUseCase
import com.kldmohammed.yassir.movapp.features.movies.ui.all.MoviesViewModel
import com.kldmohammed.yassir.movapp.features.movies.ui.details.MoviesDetailsViewModel
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit


val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}


val movieModule = module {
    single<MoviesDataSource> { MoviesDataSourceImpl(get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }
    single { GetAllMovieUseCase(get()) }
    single { GetMovieDetailsUseCase(get()) }
    viewModel { MoviesViewModel(get()) }
    viewModel { MoviesDetailsViewModel(get()) }
}


private fun provideApiService(retrofit: Retrofit) = retrofit.create<MoviesApiService>()


private fun provideJson() = Json {
    encodeDefaults = true
    prettyPrint = true
    isLenient = true
    ignoreUnknownKeys = true
    
}


private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
//        .addConverterFactory(provideJson().asConverterFactory("application/json".toMediaTypeOrNull()!!))
        .client(okHttpClient)
        .build()


private fun provideOkHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        //		.retryOnConnectionFailure(true)
        .addInterceptor(okHttpInterceptor())
        .addInterceptor(apiKeyInterceptor())
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

private fun okHttpInterceptor() = Interceptor { chain ->
    
    val request: Request = chain.request()
    
    
    val originalHttpUrl: HttpUrl = request.url
    
    val url = originalHttpUrl.newBuilder()
        .addQueryParameter("api_key", "92eac0205b154b45d0d35306e2e62933")
        .build()
    
    request.newBuilder()
        .url(url)
        .header("Connection", "close")
        .header("Content-Type", "application/json")
        .header("Accept", "application/json")
        
        .build()
    chain.proceed(request)
}


private fun apiKeyInterceptor() = Interceptor { chain ->
    val url = chain
        .request()
        .url
        .newBuilder()
        .addQueryParameter("api_key", "92eac0205b154b45d0d35306e2e62933")
        .build()
    chain.proceed(chain.request().newBuilder().url(url).build())
}

