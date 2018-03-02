package com.vinaylogics.firstcomponentapp.listeners

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by vinay on 02-03-2018.
 */
interface OnRecyclerViewItemClickListener<V:RecyclerView.ViewHolder, S> {
    fun onRecyclerViewItemClick(v:V?, view:View?, data:S?, position: Int)
}