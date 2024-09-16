package com.skywalker.app.data.model

import com.google.gson.annotations.SerializedName

data class Specie(
    @SerializedName("name")
    val name: String = "",
    @SerializedName("designation")
    val designation: String = "",
    @SerializedName("language")
    val language: String = ""
)


