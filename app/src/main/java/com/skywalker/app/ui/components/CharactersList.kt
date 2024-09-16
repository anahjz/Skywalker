package com.skywalker.app.ui.components

import androidx.compose.foundation.lazy.*

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import com.skywalker.app.data.model.StarWarsCharacter
import com.skywalker.app.ui.viewModel.ViewModelMain


fun <T : Any> LazyListScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    key: ((T?) -> Any),
    itemContent: @Composable LazyItemScope.(T?) -> Unit
) {
    items(
        count = lazyPagingItems.itemCount,
        key = key.let { { index -> lazyPagingItems[index].let(it) } }
    ) { index ->
        itemContent(lazyPagingItems[index])
    }
}

@Composable
fun CharactersList(
    items: LazyPagingItems<StarWarsCharacter>,
    navController: NavController,
    viewModelMain: ViewModelMain = hiltViewModel(),
    itemDetailId: (id: Int) -> Unit
) {
    val species by remember { viewModelMain.listSpecies }

    LazyColumn {
        items(
            lazyPagingItems = items,
            key = { item -> item?.name ?: "" }
        ) { item ->
            item?.let {
                CharacterItem(item = item, itemDetailId)
            }
        }
    }
}