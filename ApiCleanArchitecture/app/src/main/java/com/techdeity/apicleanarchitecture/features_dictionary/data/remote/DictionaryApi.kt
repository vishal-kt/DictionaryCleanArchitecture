package com.techdeity.apicleanarchitecture.features_dictionary.data.remote

import com.techdeity.apicleanarchitecture.features_dictionary.data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        // path annotation h ye url se word ko replace kr dega with mention word
     @Path("word") word:String
    ):List<WordInfoDto>


    companion object{
        const val BASE_URL ="https://api.dictionaryapi.dev/"
    }
}