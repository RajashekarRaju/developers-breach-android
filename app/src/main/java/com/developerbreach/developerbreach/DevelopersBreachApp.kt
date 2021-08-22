package com.developerbreach.developerbreach

import android.app.Application
import android.os.Build
import androidx.work.*
import com.developerbreach.developerbreach.worker.ArticleRefreshWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class DevelopersBreachApp : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        delayedInit()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupRecurringWork() {

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        setRequiresDeviceIdle(true)
                    }
                }.build()

        // creates and executes the same work once a day.
        val repeatingRequest: PeriodicWorkRequest =
                PeriodicWorkRequestBuilder<ArticleRefreshWorker>(1, TimeUnit.DAYS)
                        .setConstraints(constraints)
                        .build()

        /**
         * Create an instance for [WorkManager] with context and work properties which are
         * required by work manager do complete the task.
         */
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                ArticleRefreshWorker.WORKER_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                repeatingRequest
        )
    }

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }
}