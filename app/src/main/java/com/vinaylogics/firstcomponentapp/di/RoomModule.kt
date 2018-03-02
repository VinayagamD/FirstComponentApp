package com.vinaylogics.firstcomponentapp.di

import android.app.Application
import android.arch.persistence.room.Room
import com.vinaylogics.firstcomponentapp.data.ListItemDataBase
import com.vinaylogics.firstcomponentapp.data.ListItemRepository
import android.arch.lifecycle.ViewModelProvider
import com.vinaylogics.firstcomponentapp.RoomDemoApplication
import javax.inject.Singleton
import dagger.Provides
import com.vinaylogics.firstcomponentapp.data.ListItemDao
import com.vinaylogics.firstcomponentapp.viewmodel.CustomViewModelFactory
import dagger.Module


/**
 * Created by vinay on 01-03-2018.
 */
@Module
class RoomModule (application: RoomDemoApplication) {

    val database:ListItemDataBase
     init {
      database   = Room.databaseBuilder(
                 application,
                 ListItemDataBase::class.java,
                 "ListItem.db"
         ).build()
     }

    @Provides
    @Singleton
    fun provideListItemRepository(listItemDao: ListItemDao): ListItemRepository {
        return ListItemRepository(listItemDao)
    }

    @Provides
    @Singleton
    fun provideListItemDao(database: ListItemDataBase): ListItemDao {
        return database.listItemDao()
    }

    @Provides
    @Singleton
    fun provideListItemDatabase()=database

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: ListItemRepository): ViewModelProvider.Factory {
        return CustomViewModelFactory(repository)
    }
}
