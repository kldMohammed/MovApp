package com.kldmohammed.yassir.movapp.features.movies.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kldmohammed.androidtechtask.common.UiState
import com.kldmohammed.yassir.movapp.features.movies.domain.model.MovieDetails
import com.kldmohammed.yassir.movapp.features.movies.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.launch

class MoviesDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {
    
    
    val uiSate: LiveData<UiState<MovieDetails>> get() = _uiState
    
    private val _uiState: MutableLiveData<UiState<MovieDetails>> = MutableLiveData()
    
    
    fun loadMovies(movieId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading()
            val result = getMovieDetailsUseCase(movieId)
            result.fold(onSuccess = {
                _uiState.value = UiState.Success(it)
            }, onFailure = {
                _uiState.value = UiState.Error(it)
            })
        }
    }
}