package com.kldmohammed.androidtechtask.common.extensions

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

fun Context.vibrate() {
	val vibrator: Vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
	} else {
		vibrator.vibrate(500)
	}
}