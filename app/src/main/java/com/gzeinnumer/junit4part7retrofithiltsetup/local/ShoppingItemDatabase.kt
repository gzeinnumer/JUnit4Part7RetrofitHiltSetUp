package com.gzeinnumer.junit4part7retrofithiltsetup.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gzeinnumer.junit4part7retrofithiltsetup.local.ShoppingDao
import com.gzeinnumer.junit4part7retrofithiltsetup.local.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingItemDatabase : RoomDatabase() {

    abstract fun shoppingDao(): ShoppingDao
}