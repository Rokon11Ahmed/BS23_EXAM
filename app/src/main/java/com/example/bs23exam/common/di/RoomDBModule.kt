package com.example.bs23exam.common.di

import android.content.Context
import androidx.room.Room
import com.example.bs23exam.common.local_db.RoomDAO
import com.example.bs23exam.common.local_db.RoomDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {
    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): RoomDB = Room.databaseBuilder(
        context,
        RoomDB::class.java,
        RoomDB.DB_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideRoomDAO(db: RoomDB): RoomDAO = db.getDao()
}