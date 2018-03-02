package com.vinaylogics.firstcomponentapp.create


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.vinaylogics.firstcomponentapp.R
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import com.vinaylogics.firstcomponentapp.RoomDemoApplication






/**
 * A simple [Fragment] subclass.
 */
class CreateFragment() : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    companion object {
        fun newInstance(): CreateFragment {
            return CreateFragment()
        }
    }


}// Required empty public constructor
