package com.mukesh.contactmachinetask.di

import android.app.Application
import androidx.room.Room
import com.mukesh.contactmachinetask.common.STRING_ALIAS
import com.mukesh.contactmachinetask.db.ContactDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    /**
     * Provide Data Base Name
     * */
    @Provides
    @Singleton
    fun dbName(application: Application): String = application.applicationContext.getString(
        STRING_ALIAS.app_name).replace(" ", "_")


    /**
     * Provide Data Base Singleton Instance
     * */
    @Provides
    @Singleton
    fun provideDataBase(application: Application, dbName: String): ContactDb =
        Room.databaseBuilder(application, ContactDb::class.java, dbName).fallbackToDestructiveMigration().build()


}