package com.example.bs23exam.common.models.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "owner")
data class OwnerDetails(
    @PrimaryKey
    val id: Int?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "avatar_url")
    var avatar_url: String?
)