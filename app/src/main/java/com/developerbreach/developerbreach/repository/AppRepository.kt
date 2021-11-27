package com.developerbreach.developerbreach.repository

import android.content.Context
import com.developerbreach.developerbreach.repository.database.DatabaseRepository
import com.developerbreach.developerbreach.repository.database.getDatabaseInstance
import com.developerbreach.developerbreach.repository.local.LocalRepository
import com.developerbreach.developerbreach.repository.network.NetworkRepository
import com.developerbreach.developerbreach.repository.network.NetworkService


class AppRepository(
    context: Context
) {
    val databaseRepository = DatabaseRepository(getDatabaseInstance(context))
    val networkRepository = NetworkRepository(NetworkService())
    val localRepository = LocalRepository(context)
}