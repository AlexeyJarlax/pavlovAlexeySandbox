package com.pavlovalexey.pavlovAlexeySandbox.di

/** Павлов Алексей https://github.com/AlexeyJarlax */

import android.content.Context
import com.pavlovalexey.pavlovAlexeySandbox.repository.InstalledAppsRepository
import com.pavlovalexey.pavlovAlexeySandbox.repository.InstalledAppsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppsModule {

    @Provides
    @Singleton
    fun provideInstalledAppsRepository(
        @ApplicationContext context: Context
    ): InstalledAppsRepository = InstalledAppsRepositoryImpl(context)
}