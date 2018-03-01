package com.vinaylogics.firstcomponentapp.data

import javax.inject.Inject
import android.arch.lifecycle.LiveData



/**
 * Created by vinay on 01-03-2018.
 */
class ListItemRepository( @Inject val listItemDao: ListItemDao) {
    fun getListOfData(): LiveData<List<ListItem>> {
        return listItemDao.getListItems()
    }

    fun getListItem(itemId: String): LiveData<ListItem> {
        return listItemDao.getListItemById(itemId)
    }

    fun createNewListItem(listItem: ListItem): Long? {
        return listItemDao.insertListItem(listItem)
    }

    fun deleteListItem(listItem: ListItem) {
        listItemDao.deleteListItem(listItem)
    }
}