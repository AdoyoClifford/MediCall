package com.example.medicall.di

import android.app.Application
import androidx.room.Room
import com.example.medicall.data.ContactDataBase
import com.example.medicall.data.ContactRepository
import com.example.medicall.data.ContactRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesContactDataBase(app: Application): ContactDataBase {
        return Room.databaseBuilder(
            app,
            ContactDataBase::class.java,
            "contactDb"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideContactRepository(db: ContactDataBase): ContactRepository {
        return ContactRepositoryImpl(db.dao)
    }

}