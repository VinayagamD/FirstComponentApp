package com.vinaylogics.firstcomponentapp.adapters

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.vinaylogics.firstcomponentapp.R
import com.vinaylogics.firstcomponentapp.data.ListItem
import com.vinaylogics.firstcomponentapp.listeners.OnRecyclerViewItemClickListener
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_data.view.*

/**
 * Created by vinay on 02-03-2018.
 */
class CustomAdapter(val listItems: MutableList<ListItem> ? = ArrayList(), val inflater: LayoutInflater) : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {

    var listener: OnRecyclerViewItemClickListener<CustomViewHolder, ListItem>? = null

    fun setListeners(listener: OnRecyclerViewItemClickListener<CustomViewHolder,ListItem>?){
        this.listener= listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
            CustomViewHolder(inflater.inflate(R.layout.item_data, parent, false))

    override fun getItemCount(): Int = listItems.orEmpty().size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
       holder.setData(listItems?.get(position))
    }


    inner class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private var itemCircleImageView: CircleImageView? = null
        private var dateTimeTextView: TextView? = null
        private var messageTextView: TextView? = null
        private var rootLayout: ConstraintLayout? = null
        private var itemProgressBar: ProgressBar? = null


        init {
            try{
                itemCircleImageView = view.listCircleImageView
                dateTimeTextView= view.dateAndTimeTextView
                messageTextView = view.messageTextView
                rootLayout = view.rootLayout
                itemProgressBar = view.itemProgressBar
                view.setOnClickListener({
                    v ->
                    try {
                        assert(listener != null){
                            listener?.onRecyclerViewItemClick(this, v as View, listItems.orEmpty()[adapterPosition],adapterPosition)!!
                        }
                    } catch (e: Throwable) {
                        Log.d(TAG,e.message,e)
                    }
                })

            }catch( t:Throwable){
                t.stackTrace
            }


        }

        fun setData(listItem: ListItem?){
            listItem?.colorResource?.let { itemCircleImageView?.setImageResource(it) }
            listItem?.itemId?.let { dateTimeTextView?.text = it }
            listItem?.message?.let { messageTextView?.text=it }
            itemProgressBar?.visibility = View.GONE


        }
    }

    companion object {
        val TAG: String = CustomAdapter::class.java.simpleName
    }
}