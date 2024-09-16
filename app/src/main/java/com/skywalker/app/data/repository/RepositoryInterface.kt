package com.skywalker.app.data.repository

import androidx.paging.PagingData
import com.skywalker.app.data.model.Film
import com.skywalker.app.data.model.StarWarsCharacter
import kotlinx.coroutines.flow.Flow


interface RepositoryInterface {
    fun getAllCharacters(): Flow<PagingData<StarWarsCharacter>>
    fun getSearchedCharacters(searchText: String): Flow<PagingData<StarWarsCharacter>>
    suspend fun getSpecies(id: String)
    suspend fun getHomeWorld(id: String)
    suspend fun getFilms(id: String): List<Film>
    suspend fun getCharacter(id: String): StarWarsCharacter
}