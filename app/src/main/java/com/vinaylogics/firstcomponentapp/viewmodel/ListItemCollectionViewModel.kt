/*
 * *
 *  * Copyright (C) 2017 Ryan Kay Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.vinaylogics.firstcomponentapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.vinaylogics.firstcomponentapp.data.ListItem
import com.vinaylogics.firstcomponentapp.data.ListItemRepository


/**
 * Created by R_KAY on 8/3/2017.
 */

class ListItemCollectionViewModel internal constructor(private val repository: ListItemRepository) : ViewModel() {

    val listItems: LiveData<List<ListItem>>
        get() = repository.getListData()

    fun deleteListItem(listItem: ListItem) {
        val deleteItemTask = DeleteItemTask()
        deleteItemTask.execute(listItem)
    }

    private inner class DeleteItemTask : AsyncTask<ListItem, Void, Void>() {

        override fun doInBackground(vararg item: ListItem): Void? {
            repository.deleteListItem(item[0])
            return null
        }
    }

}

