package com.nhinhnguyenuit.jetpackcomposedisplaylist.di

import android.app.Application
import android.content.ClipData.Item
import android.content.Context
import androidx.room.Room
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.repository.ItemRepositoryImpl
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local.AppDatabase
import com.nhinhnguyenuit.jetpackcomposedisplaylist.data.source.local.ItemDao
import com.nhinhnguyenuit.jetpackcomposedisplaylist.domain.repository.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase{
        return Room.databaseBuilder(context,AppDatabase::class.java,"app_database").build()
    }

    @Provides
    fun provideItemDao(database: AppDatabase): ItemDao = database.itemDao()

    @Provides
    fun provideItemRepository(dao: ItemDao): ItemRepository = ItemRepositoryImpl(dao)
}