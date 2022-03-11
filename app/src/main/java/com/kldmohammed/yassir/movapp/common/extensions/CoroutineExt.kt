package com.kldmohammed.yassir.movapp.common.extensions

import com.kldmohammed.androidtechtask.common.exceptions.ApiException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/*
suspend fun <R> Call<R>.asUiState(): UiState<R> {
	return suspendCoroutine { cont ->
		//        cont.resume(UiState.Loading())
		enqueue(object : Callback<R> {
			override fun onFailure(call: Call<R>, t: Throwable) {
				t.printStackTrace()
				//  Timber.i(t)
				cont.resume(UiState.Error(t))
			}
			
			override fun onResponse(call: Call<R>, response: Response<R>) {
				if (response.isSuccessful) {
					//  Timber.i(response.body().toString())
					cont.resume(UiState.Success(response.body()!!))
				} else {
					//  Timber.d("msg sssss ee %s", response.errorBody()?.string())
					cont.resume(
						UiState.Error(
							ApiException(
								code = response.code(),
								errorMessage = response.message()
							)
						)
					)
				}
			}
			
		})
	}
}
*/



suspend fun <R> Call<R>.asUiState(): Result<R> {
	return suspendCoroutine { cont ->
		enqueue(object : Callback<R> {
			override fun onFailure(call: Call<R>, t: Throwable) {
				t.printStackTrace()
				cont.resume(Result.failure(t))
			}
			
			override fun onResponse(call: Call<R>, response: Response<R>) {
				if (response.isSuccessful) {
					cont.resume(Result.success(response.body()!!))
				} else {
					cont.resume(
						Result.failure(
							ApiException(
								code = response.code(),
								errorMessage = response.message()
							)
						)
					)
				}
			}
			
		})
	}
}