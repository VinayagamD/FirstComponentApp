package com.vinaylogics.firstcomponentapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by vinay on 01-03-2018.
 */
@Database(entities = [(ListItem::class)], version = 1)
abstract class ListItemDataBase :RoomDatabase() {

    abstract fun listItemDao():ListItemDao
}
