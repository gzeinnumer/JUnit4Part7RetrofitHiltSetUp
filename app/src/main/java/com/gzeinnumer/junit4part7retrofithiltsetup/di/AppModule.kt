package com.gzeinnumer.junit4part7retrofithiltsetup.di

import android.content.Context
import androidx.room.Room
import com.gzeinnumer.junit4part7retrofithiltsetup.data.remote.PixabayAPI
import com.gzeinnumer.junit4part7retrofithiltsetup.data.local.ShoppingDao
import com.gzeinnumer.junit4part7retrofithiltsetup.data.local.ShoppingItemDatabase
import com.gzeinnumer.junit4part7retrofithiltsetup.other.Constant.BASE_URL
import com.gzeinnumer.junit4part7retrofithiltsetup.other.Constant.DATABASE_NAME
import com.gzeinnumer.junit4part7retrofithiltsetup.repositories.DefaultShoppingRepository
import com.gzeinnumer.junit4part7retrofithiltsetup.repositories.ShoppingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideShoppingItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ShoppingItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun privideShoppingDao(
        database: ShoppingItemDatabase
    ) = database.shoppingDao()

    @Singleton
    @Provides
    fun providesPixabayApi(): PixabayAPI{
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java);
    }

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        dao: ShoppingDao,
        api: PixabayAPI
    ) = DefaultShoppingRepository(dao, api) as ShoppingRepository
}