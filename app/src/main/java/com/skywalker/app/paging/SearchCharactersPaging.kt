package com.skywalker.app.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skywalker.app.data.model.StarWarsCharacter
import com.skywalker.app.data.model.StarWarsModel
import com.skywalker.app.data.remoteDatasource.RetrofitServiceInterface
import retrofit2.HttpException
import java.io.IOException


class SearchCharactersPaging(
    private val starWarsApi: RetrofitServiceInterface,
    private val textSearch: String
) : PagingSource<Int, StarWarsCharacter>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StarWarsCharacter> {
        return try {
            val nextPage = params.key ?: 1
            val response = starWarsApi.starWarsApiService().getSearchingCharacters(textSearch)
            val starWarsModel = response.body() ?: StarWarsModel()
            val endPagination = starWarsModel.characters.isEmpty() || (starWarsModel.next == null)
            LoadResult.Page(
                data = starWarsModel.characters,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (endPagination) null else nextPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, StarWarsCharacter>): Int? {
        return state.anchorPosition
    }
}