package com.hellguy39.hellbooks.prefs

import android.content.Context
import android.content.SharedPreferences
import com.hellguy39.hellbooks.common.converter.ListOfIntsConverter
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


class PrefsStorage
@Inject
constructor(
    @ApplicationContext context: Context
) {

    private val listOfIntsConverter by lazy { ListOfIntsConverter() }

    private val storage by lazy {
        context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE)
    }

    private fun edit(block: SharedPreferences.Editor.() -> Unit) {
        with(storage.edit()) {
            block.invoke(this)
            apply()
        }
    }

    private var lastReadQueue: String
        get() = storage.getString(Keys.lastReadQueueKey, DefaultValues.emptyLastReadQueue) ?: DefaultValues.emptyLastReadQueue
        set(value) = edit { putString(Keys.lastReadQueueKey, value) }

    private var _lastReadQueueFlow = MutableStateFlow(
        listOfIntsConverter.stringToListOfInts(lastReadQueue)
    )
    var lastReadQueueFlow = _lastReadQueueFlow.asStateFlow()

    fun addToLastReadQueue(id: Int) {
        val list = listOfIntsConverter.stringToListOfInts(lastReadQueue)
            .toMutableList()
            .apply { remove(id) }
        val updatedList = listOf(id) + list
        lastReadQueue = listOfIntsConverter.listOfIntsToString(updatedList)
        _lastReadQueueFlow.update { updatedList }
    }

    object Keys {
        const val lastReadQueueKey = "lastReadQueueKey"
    }

    object DefaultValues {
        const val emptyLastReadQueue = "[]"
    }

    companion object {
        const val STORAGE_NAME = "local_shared_prefs"
    }
}