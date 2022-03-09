package com.kldmohammed.androidtechtask.common
/**
 * A generic class that holds a value .
 * @param <T>
 */

import com.kldmohammed.androidtechtask.common.Result.Success

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
//    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
//            Loading -> "Loading"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val <T> Result<T>.succeeded
    get() = this is Result.Success && data != null

fun <T> T.toSuccess() = Result.Success(this)


fun <T> Result<T>.getOrNull() = if (this is Result.Success) data else null
fun <T> Result<T>.require() =
    if (this is Result.Success) data else throw RuntimeException("Required result not available")

fun <T> Result<T>.requireError() =
    if (this is Result.Error) exception else throw RuntimeException("Required result not available")

fun <T, R> Result<T>.map(transformer: (T) -> R): Result<R> = when (this) {
    is Result.Success -> transformer.invoke(data).toSuccess()
    is Result.Error -> Result.Error(exception)
}

fun <T, R> Result<T>.mapOrError(transformer: (T) -> R?): Result<R> = when (this) {
    is Success -> transformer.invoke(data)?.toSuccess() ?: Result.Error(Exception())
    is Result.Error -> Result.Error(exception)
}

fun <T, R> Result<List<T>>.mapList(transformer: (T) -> R): Result<List<R>> = when (this) {
    is Success -> data.map { transformer.invoke(it) }.toSuccess()
    is Result.Error -> Result.Error(exception)
}
