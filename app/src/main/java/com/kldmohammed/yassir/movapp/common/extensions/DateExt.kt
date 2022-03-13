package com.kldmohammed.yassir.movapp.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.format(pattern: String = "yyyy-MM-dd HH:mm:ss") =
    SimpleDateFormat(pattern, Locale.US).format(this)