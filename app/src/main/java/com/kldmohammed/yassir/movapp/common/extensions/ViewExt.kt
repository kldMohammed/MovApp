/*
 * Copyright 2020 Enaya. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * created by Khalid Mohammed
 * last modified on 7/19/20 11:36 AM
 *
 */

package com.kldmohammed.androidtechtask.common.extensions

import android.graphics.Color.red
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.children
import com.google.android.material.snackbar.Snackbar
import com.kldmohammed.androidtechtask.R

fun View.show() {
	animate().alpha(1.0f).duration = 300
	visibility = View.VISIBLE
}

fun View.gone() {
	animate().alpha(0.0f).duration = 300
	visibility = View.GONE
}



fun View.longSnackBar(message: CharSequence) = Snackbar
	.make(this, message, Snackbar.LENGTH_LONG)
	.apply { show() }


fun View.errorSnackBar(message: Int) = Snackbar
	.make(this, message, Snackbar.LENGTH_LONG)
	.apply {
		context.vibrate()
		//generate the snackbar
		//  val sb = Snackbar.make(rootView, snack.text, duration)
		//set te action button text color
		setActionTextColor(context.resources.getColor(R.color.white))
		//Get the view of the snackbar
		// val sbView = view
		//set background color
		/*  val params = view.layoutParams as FrameLayout.LayoutParams
		  params.gravity = Gravity.BOTTOM
		  view.layoutParams = params*/
		
		view.setBackgroundColor(context.resources.getColor(R.color.red))
		//Get the textview of the snackbar text
		
		val textView = view.findViewById(R.id.snackbar_text) as TextView
		//set text color
		textView.setTextColor(context.resources.getColor(R.color.white))
		//increase max lines of text in snackbar. default is 2.
		textView.maxLines = 10
		show()
	}

fun View.errorSnackBar(message: String) = Snackbar
	.make(this, message, Snackbar.LENGTH_LONG)
	.apply {
		context.vibrate()
		//generate the snackbar
		//  val sb = Snackbar.make(rootView, snack.text, duration)
		//set te action button text color
		setActionTextColor(context.resources.getColor(R.color.white))
		//Get the view of the snackbar
		// val sbView = view
		//set background color
		/*  val params = view.layoutParams as FrameLayout.LayoutParams
		  params.gravity = Gravity.BOTTOM
		  view.layoutParams = params*/
		
		view.setBackgroundColor(context.resources.getColor(R.color.red))
		//Get the textview of the snackbar text
		
		val textView = view.findViewById(R.id.snackbar_text) as TextView
		//set text color
		textView.setTextColor(context.resources.getColor(R.color.white))
		//increase max lines of text in snackbar. default is 2.
		textView.maxLines = 10
		show()
	}