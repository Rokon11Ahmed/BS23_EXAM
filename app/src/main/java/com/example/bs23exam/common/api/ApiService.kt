package com.example.bs23exam.common.api

import com.example.bs23exam.common.models.OwnerDetailsResponse
import com.example.bs23exam.common.models.RepositoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    suspend fun getRepositoryListFromServer(
        @Query("q") searchText: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Response<RepositoryResponse>

    @GET("users/{userName}")
    suspend fun getOwnerDetailsFromServer(
        @Path("userName") userName: String
    ): Response<OwnerDetailsResponse>
}