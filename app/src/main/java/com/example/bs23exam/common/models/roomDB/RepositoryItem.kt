package com.example.bs23exam.common.models.roomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bs23exam.common.models.Owner

@Entity(tableName = "repository")
data class RepositoryItem(
    @PrimaryKey
    val id: Int?,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "full_name")
    var full_name: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "owner")
    var owner: String?,
    @ColumnInfo(name = "updated_at")
    var updated_at: String?
)
