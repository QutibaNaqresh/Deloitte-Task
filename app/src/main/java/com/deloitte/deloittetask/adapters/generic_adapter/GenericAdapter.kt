package com.deloitte.deloittetask.adapters.generic_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deloitte.deloittetask.R
import com.deloitte.deloittetask.adapters.view_holders.ArticleViewHolder
import com.deloitte.deloittetask.base.BaseAdapter
import com.deloitte.deloittetask.repository.local_data_source.models.Article
import com.deloitte.deloittetask.repository.remote_data_source.models.output_dtos.ArticleDTO
import javax.inject.Inject

class GenericAdapter @Inject constructor():BaseAdapter<Any>() {
    override fun getLayoutId(item: Any): Int {
      return  when(item){

          is ArticleDTO,
          is Article ->
              R.layout.layout_article_item
            else ->
                R.layout.layout_article_item
        }
    }

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
              R.layout.layout_article_item->{
                  ArticleViewHolder(
                      DataBindingUtil.inflate(
                          LayoutInflater.from(parent.context), viewType, parent, false
                      )
                  )
              }
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