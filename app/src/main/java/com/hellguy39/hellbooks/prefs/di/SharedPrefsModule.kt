package com.hellguy39.hellbooks.prefs.di

import android.content.Context
import com.hellguy39.hellbooks.prefs.PrefsStorage
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SharedPrefsModule {

    fun providePrefsStorage(
        @ApplicationContext context: Context
    ): PrefsStorage {
        return PrefsStorage(context)
    }

}