package com.gzeinnumer.junit4part7retrofithiltsetup.repositories

import androidx.lifecycle.LiveData
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.PixabayAPI
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.responses.ImageResponse
import com.gzeinnumer.junit4part7retrofithiltsetup.local.ShoppingDao
import com.gzeinnumer.junit4part7retrofithiltsetup.local.ShoppingItem
import com.gzeinnumer.junit4part7retrofithiltsetup.other.Resource
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository{
    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observerAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observerTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknow error accured", null)
            } else{
                Resource.error("An unknow error accured", null)
            }
        } catch (e: Exception){
            Resource.error("Couldn't reach ther server. Check your internet connection", null)
        }
    }
}