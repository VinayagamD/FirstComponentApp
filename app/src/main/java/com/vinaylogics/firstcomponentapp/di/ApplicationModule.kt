package com.vinaylogics.firstcomponentapp.di

import com.vinaylogics.firstcomponentapp.RoomDemoApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by vinay on 01-03-2018.
 */
@Module
class ApplicationModule(val application: RoomDemoApplication) {

    @Provides
    @Singleton
    fun provideRoomDemoApplication()=application


}