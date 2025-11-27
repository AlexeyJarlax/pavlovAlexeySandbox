package com.pavlovalexey.pavlovAlexeySandbox.di

import com.pavlovalexey.pavlovAlexeySandbox.repository.WorkoutRepository
import com.pavlovalexey.pavlovAlexeySandbox.repository.WorkoutRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import androidx.annotation.Keep

@Keep
@Module
@InstallIn(SingletonComponent::class)
abstract class WorkoutRepositoryModule {

    @Binds
    abstract fun bindWorkoutRepository(
        impl: WorkoutRepositoryImpl
    ): WorkoutRepository
}