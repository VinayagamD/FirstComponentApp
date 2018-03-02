package com.vinaylogics.firstcomponentapp.di

import android.app.Application
import android.app.ListFragment
import com.vinaylogics.firstcomponentapp.RoomDemoApplication
import com.vinaylogics.firstcomponentapp.create.CreateFragment
import com.vinaylogics.firstcomponentapp.detail.DetailFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by vinay on 01-03-2018.
 */

@Singleton
@Component(modules = [(ApplicationModule::class), (RoomModule::class)])
interface ApplicationComponent {

    fun inject(listFragment: ListFragment)
    fun inject(createFragment: CreateFragment)
    fun inject(detailFragment: DetailFragment)


    fun application():RoomDemoApplication
}