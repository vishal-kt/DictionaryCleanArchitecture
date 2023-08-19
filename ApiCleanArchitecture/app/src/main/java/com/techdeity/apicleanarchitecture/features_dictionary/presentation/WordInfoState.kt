package com.techdeity.apicleanarchitecture.features_dictionary.presentation

import com.techdeity.apicleanarchitecture.features_dictionary.domain.model.WordInfo

data class WordInfoState(
    val wordInfoItems:List<WordInfo> = emptyList(),
    val isLoading :Boolean = false

)
