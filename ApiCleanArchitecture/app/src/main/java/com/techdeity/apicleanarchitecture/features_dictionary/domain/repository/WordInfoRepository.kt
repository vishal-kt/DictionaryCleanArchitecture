package com.techdeity.apicleanarchitecture.features_dictionary.domain.repository

import com.techdeity.apicleanarchitecture.core.util.Resource
import com.techdeity.apicleanarchitecture.features_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow


interface WordInfoRepository {

    fun getWordInfo(word:String):Flow<Resource<List<WordInfo>>>
}