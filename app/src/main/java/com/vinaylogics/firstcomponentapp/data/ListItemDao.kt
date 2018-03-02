package com.vinaylogics.firstcomponentapp.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query


/**
 * Created by vinay on 01-03-2018.
 */

@Dao
interface ListItemDao {

    @Query("SELECT * FROM ListItem")
    fun getListItems():LiveData<MutableList<ListItem>>

    @Query("SELECT * FROM ListItem WHERE itemId = :itemId")
    fun getListItemById(itemId:String):LiveData<ListItem>

    @Insert(onConflict = REPLACE)
    fun insertListItem(listItem: ListItem):Long

    @Delete
    fun deleteListItem(listItem: ListItem)

}