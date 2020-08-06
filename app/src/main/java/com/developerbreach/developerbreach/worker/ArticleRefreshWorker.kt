package com.developerbreach.developerbreach.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.bumptech.glide.load.HttpException
import com.developerbreach.developerbreach.repository.ArticleRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance

/**
 * A class that performs work synchronously on a background thread provided by [Worker].
 */
class ArticleRefreshWorker(
        context: Context,
        workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    companion object {
        const val WORKER_NAME = "com.developersbreach.developersbreach.work.RefreshDataWorker"
    }

    /**
     * Override this method to do your actual background processing.  This method is called on a
     * background thread - you are required to **synchronously** do your work and return the
     * [Result] from this method.  Once you return from this
     * method, the Worker is considered to have finished what its doing and will be destroyed.
     *
     * A Worker is given a maximum of ten minutes to finish its execution and return a
     * [Result].  After this time has expired, the Worker will
     * be signalled to stop.
     */
    override suspend fun doWork(): Result {
        val database = getDatabaseInstance(applicationContext)
        val repository = ArticleRepository(database)
        try {
            // This task gets data from internet and adds them to database.
            // Also deletes any initial data found.
            repository.refreshArticles()
        } catch (e: HttpException) {
            // Return failure as result and print exception.
            return Result.retry()
        }

        // Indicate whether the task finished successfully with the Result.
        return Result.success()
    }
}