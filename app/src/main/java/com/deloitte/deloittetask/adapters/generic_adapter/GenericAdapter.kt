package com.deloitte.deloittetask.adapters.generic_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.adapters.view_holders.ArticleViewHolder
import com.deloitte.deloittetask.base.BaseAdapter

class GenericAdapter:BaseAdapter<Any>() {
    override fun getLayoutId(item: Any): Int {
      return  when(item){

            else ->
                R.layout.layout_article_item
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            else ->{
                ArticleViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context), viewType, parent, false
                    )
                )
            }

        }
    }
}