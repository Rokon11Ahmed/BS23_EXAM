package com.example.bs23exam.common.api

import com.example.bs23exam.common.models.OwnerDetailsResponse
import com.example.bs23exam.common.models.RepositoryRequest
import com.example.bs23exam.common.models.RepositoryResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getRepositoryListFromServer(request: RepositoryRequest): Response<RepositoryResponse>
    suspend fun getOwnerDetailsFromServer(userName: String): Response<OwnerDetailsResponse>
}