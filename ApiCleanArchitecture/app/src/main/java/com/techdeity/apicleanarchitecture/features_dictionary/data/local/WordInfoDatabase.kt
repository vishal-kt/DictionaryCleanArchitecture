package com.techdeity.apicleanarchitecture.features_dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.techdeity.apicleanarchitecture.features_dictionary.data.local.entity.WordInfoEntity


    @Database(
    entities = [WordInfoEntity::class],
    version = 1
    )
    @TypeConverters(Converters::class)
    abstract  class WordInfoDatabase:RoomDatabase() {

    abstract val dao :WordInfoDao

    }