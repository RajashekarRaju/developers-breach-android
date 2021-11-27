package com.developerbreach.developerbreach.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.developerbreach.developerbreach.utils.*
import com.developerbreach.developerbreach.utils.DatabaseProperties.ColumnFavorites

@Entity(tableName = DatabaseProperties.EntityTable.FAVORITES)
data class FavoritesEntity(

    @PrimaryKey
    @ColumnInfo(name = ColumnFavorites.ID)
    val articleId: Int,

    @ColumnInfo(name = ColumnFavorites.NAME)
    val name: String,

    @ColumnInfo(name = ColumnFavorites.BANNER)
    val banner: String
)