package com.deloitte.deloittetask.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any> constructor(
    protected var listItem: ArrayList<T> = arrayListOf<T>()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {



    fun setItems(listItems: ArrayList<T>) {
        this.listItem.clear()
        this.listItem.addAll(0, listItems)
        notifyDataSetChanged()
    }

    fun addElement(item: T) {
        this.listItem.add(item)

        notifyItemInserted(listItem.size)

    }
    fun getLastElement() = listItem[listItem.size-1]
   fun clearItems() {
        this.listItem.clear()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(parent, viewType)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        (holder as Binder<T>).bind(listItem[position])

    }

    override fun getItemCount() = listItem.size

    override fun getItemViewType(position: Int): Int {
        return getLayoutId(listItem[position])
    }

    abstract fun getLayoutId(item: T): Int
    abstract fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    internal interface Binder<T> {
        fun bind(item: T)
    }



}