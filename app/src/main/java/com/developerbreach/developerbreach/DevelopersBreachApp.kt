package com.developerbreach.developerbreach

import android.app.Application
import com.developerbreach.developerbreach.repository.AppRepository
import com.developerbreach.developerbreach.repository.database.DatabaseRepository
import com.developerbreach.developerbreach.repository.local.LocalRepository
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import timber.log.Timber

class DevelopersBreachApp : Application() {

    lateinit var databaseRepository: DatabaseRepository
    lateinit var networkRepository: NetworkRepository
    lateinit var localRepository: LocalRepository

    override fun onCreate() {
        super.onCreate()

        initializeRepository()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initializeRepository() {
        val repository = AppRepository(applicationContext)
        databaseRepository = repository.databaseRepository
        networkRepository = repository.networkRepository
        localRepository = repository.localRepository
    }
}