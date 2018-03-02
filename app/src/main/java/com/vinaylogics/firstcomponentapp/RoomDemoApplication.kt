package com.vinaylogics.firstcomponentapp

import android.app.Application
import com.vinaylogics.firstcomponentapp.di.ApplicationComponent
import com.vinaylogics.firstcomponentapp.di.ApplicationModule
import com.vinaylogics.firstcomponentapp.di.DaggerApplicationComponent
import com.vinaylogics.firstcomponentapp.di.RoomModule


/**
 * Created by vinay on 01-03-2018.
 */
class RoomDemoApplication :Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule( ApplicationModule(this))
                .roomModule( RoomModule(this))
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}