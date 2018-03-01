package com.vinaylogics.firstcomponentapp.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by vinay on 01-03-2018.
 */
abstract  class  BaseActivity: AppCompatActivity() {

    companion object {
        fun addFragmentToActivity(fragmentManager: FragmentManager,
                                  fragment: Fragment?,
                                  frameId: Int,
                                  tag: String) {

            val transaction = fragmentManager.beginTransaction()
            transaction.replace(frameId, fragment, tag)
            transaction.commit()
        }
    }
}