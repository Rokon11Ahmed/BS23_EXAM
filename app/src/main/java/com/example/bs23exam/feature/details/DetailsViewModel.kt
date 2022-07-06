package com.example.bs23exam.feature.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bs23exam.common.models.roomDB.OwnerDetails
import com.example.bs23exam.common.models.roomDB.RepositoryItem
import com.example.bs23exam.common.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repo: Repository): ViewModel() {

    fun getRepositoryDetails(repoId: Int): LiveData<RepositoryItem> = repo.getRepositoryDetailsByID(repoId)
    fun getOwnerDetails(): LiveData<OwnerDetails> = repo.getOwnerDetails()

    fun getOwnerDetailsFromServer(userName: String) = viewModelScope.launch {
        repo.getOwnerDetailsFromServer(userName)
    }
}