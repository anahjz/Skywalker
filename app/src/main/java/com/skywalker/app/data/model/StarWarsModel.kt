package com.skywalker.app.data.model

import com.google.gson.annotations.SerializedName

data class StarWarsModel(
    @SerializedName("count")
    val count: Int = 0,
    @SerializedName("next")
    val next: String = "",
    @SerializedName("previous")
    val prev: String = "",
    @SerializedName("results")
    val characters: List<StarWarsCharacter> = arrayListOf()
)






