package com.example.bs23exam.common.local_db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.bs23exam.common.models.roomDB.OwnerDetails
import com.example.bs23exam.common.models.roomDB.RepositoryItem
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDBTest: TestCase() {

    private lateinit var db: RoomDB
    private lateinit var dao: RoomDAO

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RoomDB::class.java).build()
        dao = db.getDao()
    }

    @After
    fun closeDatabase(){
        db.close()
    }

    @Test
    fun writeAndReadOwnerData() = runBlocking{
        val ownerDetails = OwnerDetails(0, "Rokon", "avatar_url")
        dao.insertOwnerDetails(ownerDetails)
        val owner = dao.getOwnerDetailsWithOutLiveData()
        assertThat(owner == ownerDetails).isTrue()
    }

    @Test
    fun writeAndReadRepository() = runBlocking {
        val repositoryItems = arrayListOf<RepositoryItem>()
        repositoryItems.add(RepositoryItem(0, "Name1", "Full Nmae 1", "Desc 1", "owner 1", "Update 1"))
        repositoryItems.add(RepositoryItem(1, "Name2", "Full Nmae 2", "Desc 2", "owner 2", "Update 2"))
        repositoryItems.add(RepositoryItem(2, "Name3", "Full Nmae 3", "Desc 3", "owner 3", "Update 3"))

        dao.insertRepositoryList(repositoryItems)
        val repositoryItem = dao.getAllRepository().getOrAwaitValue()?.find {
            it.full_name == "Full Nmae 2" && it.description == "Desc 2"
        }
        assertThat(repositoryItem != null).isTrue()
    }

}