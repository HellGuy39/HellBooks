package com.hellguy39.hellbooks.common.converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ListOfIntsConverter {

    private val moshi = Moshi.Builder().build()
    private val membersType = Types
        .newParameterizedType(List::class.java, Int::class.javaObjectType)
    private val mapOfStringsAdapter = moshi.adapter<List<Int>>(membersType)

    @TypeConverter
    fun stringToListOfInts(string: String): List<Int> {
        return mapOfStringsAdapter.fromJson(string).orEmpty()
    }

    @TypeConverter
    fun listOfIntsToString(list: List<Int>): String {
        return mapOfStringsAdapter.toJson(list)
    }
}