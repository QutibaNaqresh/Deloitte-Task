package com.deloitte.deloittetask.common

import android.widget.EditText
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @BindingAdapter("error")
    @JvmStatic
    fun setError(editText: EditText, errorMessage: String) {

    }
}