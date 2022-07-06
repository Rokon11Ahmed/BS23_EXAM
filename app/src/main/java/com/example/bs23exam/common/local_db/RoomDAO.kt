package com.example.bs23exam.common.local_db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bs23exam.common.models.roomDB.OwnerDetails
import com.example.bs23exam.common.models.roomDB.RepositoryItem

@Dao
interface RoomDAO {

    @Insert
    suspend fun insertRepositoryList(countryList : List<RepositoryItem>)

    @Query("DELETE FROM repository")
    suspend fun deleteRepository()

    @Query("SELECT * FROM repository")
    fun getAllRepository(): LiveData<List<RepositoryItem>>

    @Query("SELECT * FROM repository WHERE id=:id")
    fun getRepoDetails(id: Int): LiveData<RepositoryItem>

    @Insert
    suspend fun insertOwnerDetails(ownerDetails: OwnerDetails)

    @Query("DELETE FROM owner")
    suspend fun deleteOwner()

    @Query("SELECT * FROM owner")
    fun getOwnerDetails(): LiveData<OwnerDetails>

    @Query("SELECT * FROM owner")
    fun getOwnerDetailsWithOutLiveData(): OwnerDetails
}