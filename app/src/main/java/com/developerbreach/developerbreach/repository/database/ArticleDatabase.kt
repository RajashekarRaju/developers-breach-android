package com.developerbreach.developerbreach.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.developerbreach.developerbreach.repository.database.dao.FavoriteDao
import com.developerbreach.developerbreach.repository.database.entity.FavoritesEntity
import com.developerbreach.developerbreach.utils.DatabaseProperties

/**
 * Let room know this is class for database by annotating with [Database] and class should be
 * an abstract class and extend to [RoomDatabase].
 *
 * Pass entity class into database, version set 1 and exportSchema to false to not create
 * a separate space folder for our app database.
 */
@Database(
    entities = [FavoritesEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ArticleDatabase : RoomDatabase() {

    /**
     * @return get access to all objects mapped to database.
     */
    abstract val favoriteDao: FavoriteDao
}

// Helps to create single instance for this class.
private lateinit var INSTANCE: ArticleDatabase

/**
 * @param context needs context to build database.
 * @return a new instance for database. Perform null checks and do not perform asynchronous,
 * finally return instance for database without creating multiple.
 */
fun getDatabaseInstance(
    context: Context
): ArticleDatabase {
    synchronized(ArticleDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                DatabaseProperties.NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
