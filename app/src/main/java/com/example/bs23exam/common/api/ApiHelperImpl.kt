package com.example.bs23exam.common.api

import com.example.bs23exam.common.models.OwnerDetailsResponse
import com.example.bs23exam.common.models.RepositoryRequest
import com.example.bs23exam.common.models.RepositoryResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getRepositoryListFromServer(request: RepositoryRequest): Response<RepositoryResponse> =
        apiService.getRepositoryListFromServer(request.searchText, request.shortBy, request.order, request.perPage, request.page)

    override suspend fun getOwnerDetailsFromServer(userName: String): Response<OwnerDetailsResponse> =
        apiService.getOwnerDetailsFromServer(userName)
}