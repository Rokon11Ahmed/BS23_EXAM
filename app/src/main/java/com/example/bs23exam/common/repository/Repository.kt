package com.example.bs23exam.common.repository

import android.util.Log
import com.example.bs23exam.common.api.ApiHelper
import com.example.bs23exam.common.local_db.RoomDAO
import com.example.bs23exam.common.models.Owner
import com.example.bs23exam.common.models.RepositoryRequest
import com.example.bs23exam.common.models.roomDB.OwnerDetails
import com.example.bs23exam.common.models.roomDB.RepositoryItem
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val localDb: RoomDAO
) {

    suspend fun getRepositoryListFromServer(request: RepositoryRequest) {
        val responseBody = apiHelper.getRepositoryListFromServer(request)
        if (responseBody.isSuccessful) {
            responseBody.let {
                val dataBody = it.body()
                dataBody.let {
                    if (it?.incomplete_results == false){
                        val repositoryData = it.items
                        val repositoryList = arrayListOf<RepositoryItem>()
                        repositoryData.forEach {

                            repositoryList.add(
                                RepositoryItem(
                                    it.id,
                                    it.name,
                                    it.full_name,
                                    it.description,
                                    it.owner?.login,
                                    it.updated_at
                                )
                            )
                        }
                        localDb.deleteRepository()
                        localDb.insertRepositoryList(repositoryList)
                    }

                }

            }
        }

    }

    fun getRepositoryList() = localDb.getAllRepository()

    fun getRepositoryDetailsByID(id: Int) = localDb.getRepoDetails(id)

    suspend fun getOwnerDetailsFromServer(userName: String){
        val responseBody = apiHelper.getOwnerDetailsFromServer(userName)
        if (responseBody.isSuccessful){
            responseBody.let {
                val dataBody = it.body()
                dataBody.let { owner ->
                    if (owner != null) {
                        localDb.deleteOwner()
                        localDb.insertOwnerDetails(OwnerDetails(owner.id, owner.name, owner.avatar_url))
                    }
                }
            }
        }
    }

    fun getOwnerDetails() = localDb.getOwnerDetails()
}