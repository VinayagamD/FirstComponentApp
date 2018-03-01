package com.vinaylogics.firstcomponentapp.di

import com.vinaylogics.firstcomponentapp.RoomDemoApplication
import dagger.Module
import dagger.Provides

/**
 * Created by vinay on 01-03-2018.
 */
@Module
class ApplicationModule(val application: RoomDemoApplication) {

    @Provides
    fun provideRoomDemoApplication()=application

    @Provides
    fun provideApplication() = application
}