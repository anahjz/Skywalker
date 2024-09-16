package com.skywalker.app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.skywalker.app.common.getIdFromUrl
import com.skywalker.app.data.model.*
import com.skywalker.app.paging.AllCharactersPaging
import com.skywalker.app.data.remoteDatasource.RetrofitServiceInterface
import com.skywalker.app.paging.SearchCharactersPaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


class RepositoryImpl(private val retrofitServiceInterface: RetrofitServiceInterface) :
    RepositoryInterface {
    var starWarsCharacter = StarWarsCharacter()
    private var speciesList = arrayListOf<Specie>()
    private var filmsList = arrayListOf<Film>()

    override fun getAllCharacters(): Flow<PagingData<StarWarsCharacter>> {
        val configPager = PagingConfig(10)
        return Pager(
            config = configPager,
            pagingSourceFactory = {
                AllCharactersPaging(starWarsApi = retrofitServiceInterface)
            }
        ).flow
    }

    override fun getSearchedCharacters(searchText: String): Flow<PagingData<StarWarsCharacter>> {
        val configPager = PagingConfig(10)
        return Pager(
            configPager,
            pagingSourceFactory = {
                SearchCharactersPaging(retrofitServiceInterface, searchText)
            }
        ).flow
    }

    override suspend fun getCharacter(id: String): StarWarsCharacter {
        speciesList.clear()
        filmsList.clear()
        return withContext(Dispatchers.IO) {
            val response = retrofitServiceInterface.starWarsApiService().getCharacter(id)
            starWarsCharacter = response.body() ?: StarWarsCharacter()
            if (response.isSuccessful) {
                response.body()?.species?.let {
                    it.forEach { url ->
                        getSpecies(url.getIdFromUrl())
                    }
                }
                response.body()?.homeWorld?.let {
                    getHomeWorld(it.getIdFromUrl())
                }

            }
            starWarsCharacter
        }
    }

    override suspend fun getSpecies(id: String) {

        withContext(Dispatchers.IO) {
            val response = retrofitServiceInterface.starWarsApiService().getSpecies(id)
            val specieCharacter = response.body() ?: Specie()
            speciesList.add(specieCharacter)
            starWarsCharacter.specie = specieCharacter.name

        }


    }

    override suspend fun getHomeWorld(id: String) {
        withContext(Dispatchers.IO) {
            val response = retrofitServiceInterface.starWarsApiService().getHomeWorld(id)
            val homeWorld = response.body() ?: HomeWorld()
            starWarsCharacter.home = homeWorld.name
        }
    }

    override suspend fun getFilms(id: String): List<Film> {
        return withContext(Dispatchers.IO) {
            val response = retrofitServiceInterface.starWarsApiService().getFilms(id)
            val film = response.body() ?: Film()
            filmsList.add(film)
            filmsList

        }
    }
}