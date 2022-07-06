package com.example.bs23exam.common.models

data class RepositoryRequest(
    var searchText: String,
    var shortBy: String,
    var order: String,
    var perPage: Int,
    var page: Int
)