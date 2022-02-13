package com.gzeinnumer.junit4part7retrofithiltsetup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gzeinnumer.junit4part7retrofithiltsetup.data.local.ShoppingDao
import com.gzeinnumer.junit4part7retrofithiltsetup.data.local.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}