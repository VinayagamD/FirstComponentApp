package com.vinaylogics.firstcomponentapp.di

import android.app.Application
import android.arch.persistence.room.Room
import com.vinaylogics.firstcomponentapp.data.ListItemDataBase
import com.vinaylogics.firstcomponentapp.data.ListItemRepository
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Singleton
import dagger.Provides
import com.vinaylogics.firstcomponentapp.data.ListItemDao
import com.vinaylogics.firstcomponentapp.viewmodel.CustomViewModelFactory


/**
 * Created by vinay on 01-03-2018.
 */
class RoomModule(application: Application) {
    private val database:ListItemDataBase
    init {
        this.database = Room.databaseBuilder(
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
    fun provideListItemDatabase(application: Application)=database

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: ListItemRepository): ViewModelProvider.Factory {
        return CustomViewModelFactory(repository)
    }
}
