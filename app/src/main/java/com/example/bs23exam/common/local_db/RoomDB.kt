package com.example.bs23exam.common.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bs23exam.common.models.roomDB.OwnerDetails
import com.example.bs23exam.common.models.roomDB.RepositoryItem

@Database(entities = arrayOf(RepositoryItem::class, OwnerDetails::class), version = 1, exportSchema = false)
abstract class RoomDB: RoomDatabase() {
    abstract fun getDao(): RoomDAO

    companion object {
        const val DB_NAME = "BS23-db"
    }
}