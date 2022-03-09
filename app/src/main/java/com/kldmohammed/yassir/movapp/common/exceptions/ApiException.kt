package com.kldmohammed.androidtechtask.common.exceptions

class ApiException( val code: Int,  val errorMessage: String) : Throwable(
    "An Api Call failed with code: $code and message: $errorMessage"
)