package com.techdeity.apicleanarchitecture.features_dictionary.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.techdeity.apicleanarchitecture.features_dictionary.data.local.Converters
import com.techdeity.apicleanarchitecture.features_dictionary.data.local.WordInfoDao
import com.techdeity.apicleanarchitecture.features_dictionary.data.local.WordInfoDatabase
import com.techdeity.apicleanarchitecture.features_dictionary.data.remote.DictionaryApi
import com.techdeity.apicleanarchitecture.features_dictionary.data.repository.WordInfoRepositoryImpl
import com.techdeity.apicleanarchitecture.features_dictionary.data.util.GsonParser
import com.techdeity.apicleanarchitecture.features_dictionary.domain.model.WordInfo
import com.techdeity.apicleanarchitecture.features_dictionary.domain.repository.WordInfoRepository
import com.techdeity.apicleanarchitecture.features_dictionary.domain.use_cases.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

    @Provides
    @Singleton
    fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo {
     return GetWordInfo(repository)
    }


    @Provides
    @Singleton
    fun provideWordInfoRepository(
        dao:WordInfoDao,
        api:DictionaryApi
    ):WordInfoRepository{

        return WordInfoRepositoryImpl(api,dao)
    }

    @Provides
    @Singleton
    fun provideWordInfoDatabase(app:Application):WordInfoDatabase{
        return Room.databaseBuilder(
            app,WordInfoDatabase::class.java,"word_db")
            .addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }


    @Provides
    @Singleton
    fun provideDictionaryApi():DictionaryApi{
        return Retrofit.Builder()
            .baseUrl(DictionaryApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DictionaryApi::class.java)

    }

}