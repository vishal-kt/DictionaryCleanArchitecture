package com.techdeity.apicleanarchitecture.features_dictionary.data.local


import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.techdeity.apicleanarchitecture.features_dictionary.data.util.JsonParser
import com.techdeity.apicleanarchitecture.features_dictionary.domain.model.Meaning

@ProvidedTypeConverter
class Converters(
    private val jsonParser: JsonParser
)   {
    @TypeConverter
    fun fromMeaningsJson(json:String):List<Meaning>{
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object :TypeToken<ArrayList<Meaning>>(){}.type
        )?: emptyList()
    }

    fun toMeaningJson(meanings:List<Meaning>):String{
        return jsonParser.toJson(
            meanings,
            object :TypeToken<ArrayList<Meaning>>(){}.type
        )?:"[]"
    }



}