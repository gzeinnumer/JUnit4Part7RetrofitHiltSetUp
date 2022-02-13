package com.gzeinnumer.junit4part7retrofithiltsetup.repositories

import androidx.lifecycle.LiveData
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.responses.ImageResponse
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.responses.ImageResult
import com.gzeinnumer.junit4part7retrofithiltsetup.data.local.ShoppingItem
import com.gzeinnumer.junit4part7retrofithiltsetup.other.Resource
import retrofit2.Response

interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)
    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)
    fun observeAllShoppingItems() : LiveData<List<ShoppingItem>>
    fun observeTotalPrice() : LiveData<Float>
    suspend fun searchForImage(imageQuery: String) : Resource<ImageResponse>
}