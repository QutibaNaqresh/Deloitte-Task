package com.deloitte.deloittetask.hilt_di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.room.Room
import com.deloitte.deloittetask.common.Constants
import com.deloitte.deloittetask.common.DateHelper
import com.deloitte.deloittetask.repository.UsersRepository
import com.deloitte.deloittetask.repository.local_data_source.LocalDataSource
import com.deloitte.deloittetask.repository.local_data_source.room.AppDatabase
import com.deloitte.deloittetask.repository.local_data_source.shared_preference.SharedPreferenceHelper
import com.deloitte.deloittetask.repository.remote_data_source.APIServices
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.RoomDB.DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideUserRepository(localDataSource: LocalDataSource,remoteDataSource: APIServices , gson: Gson) = UsersRepository(localDataSource,remoteDataSource, gson)

    @Provides
    @Singleton
    fun provideLocalDataSource(appDatabase: AppDatabase, preferenceHelper: SharedPreferenceHelper) =
        LocalDataSource(appDatabase.userDao(),appDatabase.articleDao(), preferenceHelper)

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)


    @Provides
    @Singleton
    fun provideSharedPreferenceEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
        sharedPreferences.edit()

    @Provides
    @Singleton
    fun provideSharedPreferenceHelper(
        sharedPreferences: SharedPreferences,
        editor: SharedPreferences.Editor,
        gson: Gson
    ): SharedPreferenceHelper = SharedPreferenceHelper(sharedPreferences, editor, gson)

    @Provides
    @Singleton
    fun provideGson() = Gson()


}