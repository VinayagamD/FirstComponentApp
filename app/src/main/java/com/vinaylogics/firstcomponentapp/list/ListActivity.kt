package com.vinaylogics.firstcomponentapp.list

import android.os.Bundle
import com.vinaylogics.firstcomponentapp.R
import com.vinaylogics.firstcomponentapp.base.BaseActivity

class ListActivity : BaseActivity() {

    companion object {
        private val  LIST_FRAG = "LIST_FRAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val manager = supportFragmentManager
        var fragment = manager.findFragmentByTag(LIST_FRAG)
        if (fragment == null)
            fragment = ListFragment.newInstance()
        addFragmentToActivity(manager,fragment, R.id.root_activity_list, LIST_FRAG)
    }
}
