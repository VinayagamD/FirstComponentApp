package com.vinaylogics.firstcomponentapp.create

import android.os.Bundle
import com.vinaylogics.firstcomponentapp.R
import com.vinaylogics.firstcomponentapp.base.BaseActivity

class CreateActivity : BaseActivity() {
    private val CREATE_FRAG = "CREATE_FRAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        val manager = supportFragmentManager

        var fragment: CreateFragment? = manager.findFragmentByTag(CREATE_FRAG) as CreateFragment

        if (fragment == null) {
            fragment = CreateFragment.newInstance()
        }

        addFragmentToActivity(manager,
                fragment!!,
                R.id.root_activity_create,
                CREATE_FRAG
        )
    }
}
