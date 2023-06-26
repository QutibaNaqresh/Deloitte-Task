package com.deloitte.deloittetask.common

import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.adapters.generic_adapter.GenericAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @BindingAdapter("error")
    @JvmStatic
    fun setError(editText: EditText, errorMessage: String) {

    }

    @BindingAdapter("list")
    @JvmStatic
    fun setList(recyclerView: RecyclerView, list: ArrayList<Any> = arrayListOf()) {
        list?.let {
            recyclerView.adapter?.let { adapter ->
                if (adapter is GenericAdapter) {
                    adapter.setItems(it)
                }
            }
        }

    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: AppCompatImageView, url: String) {
        Glide.with(imageView.context).load(url)
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
    @BindingAdapter("isSwipeRefreshLoading")
    @JvmStatic
    fun setIsLoading(swipeRefreshLayout: SwipeRefreshLayout, isLoading:Boolean) {
        swipeRefreshLayout.isRefreshing = isLoading
    }
}