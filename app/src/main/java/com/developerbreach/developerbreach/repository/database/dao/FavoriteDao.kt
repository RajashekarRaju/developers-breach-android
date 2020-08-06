package com.developerbreach.developerbreach.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.developerbreach.developerbreach.repository.database.entity.FavoritesEntity

@Dao
interface FavoriteDao {

    /**
     * @return queries list of  favorite articles of type [LiveData] from favorites_table.
     */
    @Query("SELECT * FROM FAVORITES_TABLE")
    fun loadAllFavorites(): LiveData<List<FavoritesEntity>>

    /**
     * @param favoritesEntity inserts single favorite article into table favorites_table.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteArticle(favoritesEntity: FavoritesEntity)

    /**
     * @param favoritesEntity deletes single favorite article into table favorites_table.
     */
    @Delete
    fun deleteFavoriteArticle(favoritesEntity: FavoritesEntity)

    /**
     * Queries all data from favorites_table to perform delete operation.
     */
    @Query("DELETE FROM FAVORITES_TABLE")
    fun deleteAllFavorites()

    @get:Query("SELECT * FROM FAVORITES_TABLE")
    val favoriteList: List<FavoritesEntity>
}