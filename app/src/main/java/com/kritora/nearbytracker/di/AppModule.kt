package com.kritora.nearbytracker.di

import android.content.Context
import androidx.room.Room
import com.kritora.nearbytracker.data.local.TaskDao
import com.kritora.nearbytracker.data.local.TaskDatabase
import com.kritora.nearbytracker.data.remote.TaskApi
import com.kritora.nearbytracker.data.repository.TaskRepositoryImpl
import com.kritora.nearbytracker.domain.repository.TaskRepository
import com.kritora.nearbytracker.util.ConnectivityObserver
import com.kritora.nearbytracker.util.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTaskApi(): TaskApi {
        return Retrofit.Builder()
            .baseUrl(TaskApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTaskDatabase(@ApplicationContext context: Context): TaskDatabase {
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "tasks_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(db: TaskDatabase): TaskDao {
        return db.dao
    }

    @Provides
    @Singleton
    fun provideTaskRepository(api: TaskApi, dao: TaskDao): TaskRepository {
        return TaskRepositoryImpl(api, dao)
    }

    @Provides
    @Singleton
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver {
        return NetworkConnectivityObserver(context)
    }
}
