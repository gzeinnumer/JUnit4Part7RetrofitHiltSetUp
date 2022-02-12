package com.gzeinnumer.junit4part7retrofithiltsetup.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.responses.ImageResponse
import com.gzeinnumer.junit4part7retrofithiltsetup.local.ShoppingItem
import com.gzeinnumer.junit4part7retrofithiltsetup.other.Resource

class FakeShoppingRepository : ShoppingRepository{
    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Float {
        return shoppingItems.sumByDouble {
            it.price.toDouble()
        }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observerAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun observerTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if(shouldReturnNetworkError){
            Resource.error("Error",null)
        } else{
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }
}