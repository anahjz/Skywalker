package com.skywalker.app.ui.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.skywalker.app.common.getIdFromUrl
import com.skywalker.app.data.model.*
import com.skywalker.app.data.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelMain @Inject constructor(private val repository: RepositoryInterface) : ViewModel() {


    val allCharactersList = repository.getAllCharacters()

    val starWarsCharacter = MutableLiveData<StarWarsCharacter>()
    val filmsLiveData = MutableLiveData<List<Film>>()
    private val _foundCharactersList =
        MutableStateFlow<PagingData<StarWarsCharacter>>(PagingData.empty())
    val foundCharacterList = _foundCharactersList

    private val _isSearch = mutableStateOf(false)
    val isSearch = _isSearch
    private val _searchText = mutableStateOf("")
    val searchText = _searchText
    private var _listSpecies: MutableState<List<Specie>> = mutableStateOf(arrayListOf())

    val listSpecies = _listSpecies

    fun searchCharacter(textSearch: String) {
        _isSearch.value = true
        viewModelScope.launch {
            repository.getSearchedCharacters(textSearch).cachedIn(viewModelScope).collect {
                _foundCharactersList.value = it
            }

        }
    }

    fun getCharacter(id: String) {
        viewModelScope.launch {
            val response = repository.getCharacter(id)
            starWarsCharacter.postValue(response)
            if (response.films.isNotEmpty()) {
                var films = listOf<Film>()
                response.films.forEach {
                    films = repository.getFilms(it.getIdFromUrl())

                }
                filmsLiveData.postValue(films)
            }
        }
    }

    fun updateSearchText(text: String) {
        _searchText.value = text
    }

    fun setSearch(state: Boolean) {
        _isSearch.value = state
    }

}