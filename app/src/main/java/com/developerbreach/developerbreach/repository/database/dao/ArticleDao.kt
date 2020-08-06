package com.developerbreach.developerbreach.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.developerbreach.developerbreach.repository.database.entity.ArticleEntity

@Dao
interface ArticleDao {

    /**
     * @return queries list of articles of type [LiveData] from articles_table.
     */
    @Query("SELECT * FROM ARTICLES_TABLE")
    fun loadAllArticles(): LiveData<List<ArticleEntity>>

    /**
     * @param articleEntityList inserts list of articles into table articles_table.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articleEntityList: List<ArticleEntity>)

    /**
     * Queries all data from articles_table to perform delete operation.
     */
    @Query("DELETE FROM ARTICLES_TABLE")
    fun deleteAllArticles()

    /**
     * Queries all data from articles_table to perform search operation.
     */
    @Query("SELECT * FROM ARTICLES_TABLE")
    fun getSearchableArticles() : List<ArticleEntity>
}