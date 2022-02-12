package com.gzeinnumer.junit4part7retrofithiltsetup.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.responses.ImageResponse
import com.gzeinnumer.junit4part7retrofithiltsetup.local.ShoppingItem
import com.gzeinnumer.junit4part7retrofithiltsetup.other.Event
import com.gzeinnumer.junit4part7retrofithiltsetup.repositories.ShoppingRepository
import kotlinx.coroutines.launch
import retrofit2.http.Query

//todo 5
class ShoppingViewModel @ViewModelInject constructor(
    private val repository: ShoppingRepository
): ViewModel() {

    //todo 7
    val shoppingItems = repository.observerAllShoppingItems()

    val totalProce = repository.observerTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _currentImageUrl = MutableLiveData<String>()
    val currentImageUrl: LiveData<String> = _currentImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurImageUrl(url: String){
        _currentImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String){

    }

    fun searchImage(imageQuery: String){

    }
}