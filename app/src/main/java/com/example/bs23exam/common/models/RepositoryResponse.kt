package com.example.bs23exam.common.models

data class RepositoryResponse(
    var total_count: Int,
    var incomplete_results: Boolean,
    var items: ArrayList<GitRepo>
)

data class GitRepo(
    var id: Int?,
    var name : String?,
    var full_name: String?,
    var description: String?,
    var owner: Owner?,
    var updated_at: String?,
)

data class Owner(
    var login: String?
)