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

    /**
     * Get entity by itemId. For this App, we will pass in an ID when the detail Activity starts;
     * therefore we need not use LiveData as the Data will not change during the Activity's
     * Lifecycle.
     * @param itemId A Unique identifier for a given record within the Database.
     * @return
     */
    @Query("SELECT * FROM ListItem WHERE itemId = :itemId")
    fun getListItemById(itemId: String): LiveData<ListItem>

    /**
     * Get all entities of type ListItem
     * @return
     */
    @Query("SELECT * FROM ListItem")
    fun getListItems(): LiveData<List<ListItem>>


    /**
     * Insert a new ListItem
     * @param listItem
     */
    @Insert(onConflict = REPLACE)
    fun insertListItem(listItem: ListItem): Long?

    /**
     * Delete a given ListItem
     * @param listItem
     */
    @Delete
    fun deleteListItem(listItem: ListItem)
}