package com.developerbreach.developerbreach.repository.local

import android.content.Context
import com.developerbreach.developerbreach.model.Intro
import com.developerbreach.developerbreach.model.Options

class LocalRepository(
    private val context: Context
) {

    private val localData = LocalData

    fun introPagerListData(): List<Intro> {
        return localData.addIntroData(context)
    }

    fun optionsList(): List<Options> {
        return localData.addOptionsData(context)
    }
}