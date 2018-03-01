package com.vinaylogics.firstcomponentapp.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

/**
 * Created by vinay on 01-03-2018.
 */

@Entity
data class ListItem(
        @PrimaryKey
        @NonNull
        var itemId:String,
        var message:String,
        var colorResource:Int
)