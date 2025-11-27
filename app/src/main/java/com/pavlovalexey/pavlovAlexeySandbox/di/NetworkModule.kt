package com.pavlovalexey.pavlovAlexeySandbox.di

import com.pavlovalexey.pavlovAlexeySandbox.network.WorkoutApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import androidx.annotation.Keep

@Keep
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "http://ref.test.kolsa.ru/" /** TODO(Тестовое для спортивного приложения. Оставляю код как есть, на будущие подобные тестовые)*/

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideWorkoutApiService(retrofit: Retrofit): WorkoutApiService =
        retrofit.create(WorkoutApiService::class.java)
}