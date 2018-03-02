package com.vinaylogics.firstcomponentapp.data

import javax.inject.Inject
import android.arch.lifecycle.LiveData



/**
 * Created by vinay on 01-03-2018.
 */
class ListItemRepository
@Inject constructor(val listItemDao: ListItemDao) {

    fun getListData():LiveData<MutableList<ListItem>> = listItemDao.getListItems()

    fun getListItem(itemId: String): LiveData<ListItem> = listItemDao.getListItemById(itemId)

    fun createNewListItem(listItem: ListItem): Long? = listItemDao.insertListItem(listItem)

    fun deleteListItem(listItem: ListItem) {
        listItemDao.deleteListItem(listItem)
    }
}