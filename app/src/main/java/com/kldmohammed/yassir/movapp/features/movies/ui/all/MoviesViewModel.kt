package com.kldmohammed.yassir.movapp.features.movies.ui.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kldmohammed.androidtechtask.common.UiState
import com.kldmohammed.yassir.movapp.features.movies.domain.model.Movie
import com.kldmohammed.yassir.movapp.features.movies.domain.usecase.GetAllMovieUseCase
import kotlinx.coroutines.launch

class MoviesViewModel(
    private val getAllMovieUseCase: GetAllMovieUseCase,
) : ViewModel() {
    
    
    val uiSate: LiveData<UiState<List<Movie>>> get() = _uiState
    
    private val _uiState: MutableLiveData<UiState<List<Movie>>> = MutableLiveData()
    
    
    init {
        loadMovies()
    }
    
    private fun loadMovies() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading()
            val result = getAllMovieUseCase()
            result.fold(onSuccess = {
                _uiState.value = UiState.Success(it)
            }, onFailure = {
                _uiState.value = UiState.Error(it)
            })
        }
    }
}