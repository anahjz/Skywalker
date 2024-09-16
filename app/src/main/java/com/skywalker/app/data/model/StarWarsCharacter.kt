package com.skywalker.app.data.model

import com.google.gson.annotations.SerializedName

data class StarWarsCharacter(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("height")
    val height: String = "",
    @SerializedName("birth_year")
    val birthYear: String = "",
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("species")
    val species: List<String> = arrayListOf(),
    @Transient var specie: String = "",
    @Transient var home: String = "",
    @SerializedName("url")
    val urlPeople: String = "",
    @SerializedName("homeworld")
    val homeWorld: String = "",
    @SerializedName("films")
    val films: List<String> = arrayListOf(),
    @SerializedName("vehicles")
    val vehicles: List<String> = arrayListOf(),
    @Transient
    var filmsList: List<Film> = arrayListOf(),
)