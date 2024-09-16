package com.skywalker.app.data.remoteDatasource

import com.skywalker.app.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StarWarsApi {
    @GET("people/")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<StarWarsModel>

    @GET("species/{id}/")
    suspend fun getSpecies(@Path("id") id: String): Response<Specie>

    @GET("planets/{id}/")
    suspend fun getHomeWorld(@Path("id") id: String): Response<HomeWorld>

    @GET("films/{id}/")
    suspend fun getFilms(@Path("id") id: String): Response<Film>

    @GET("people/")
    suspend fun getSearchingCharacters(@Query("search") textSearch: String): Response<StarWarsModel>

    @GET("people/{personId}")
    suspend fun getCharacter(@Path("personId") personId: String): Response<StarWarsCharacter>

}