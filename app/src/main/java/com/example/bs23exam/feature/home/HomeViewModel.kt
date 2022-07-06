package com.example.bs23exam.feature.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crowncoder.mowall.common.other.Resource
import com.example.bs23exam.common.models.RepositoryRequest
import com.example.bs23exam.common.models.RepositoryResponse
import com.example.bs23exam.common.models.roomDB.RepositoryItem
import com.example.bs23exam.common.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    val repositoryList: LiveData<List<RepositoryItem>>
        get() = repo.getRepositoryList()

    fun getRepositoryListFromServer(request: RepositoryRequest) = viewModelScope.launch {
        repo.getRepositoryListFromServer(request)
    }
}