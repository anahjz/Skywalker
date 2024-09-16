package com.skywalker.app.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.skywalker.app.ui.components.CharactersList
import com.skywalker.app.ui.components.InputSearch
import com.skywalker.app.ui.components.ProgressIndicator
import com.skywalker.app.ui.components.Toolbar
import com.skywalker.app.ui.theme.ComposeAppTheme
import com.skywalker.app.ui.viewModel.ViewModelMain

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewModelMain: ViewModelMain = hiltViewModel()) {
    val characters = viewModelMain.allCharactersList.collectAsLazyPagingItems()
    val charactersFound = viewModelMain.foundCharacterList.collectAsLazyPagingItems()

    var showDialog by remember { mutableStateOf(false) }


    val searchText by viewModelMain.searchText
    val isSearch by viewModelMain.isSearch
    ComposeAppTheme {
        if (characters.itemCount > 0) {
            showDialog = true
        }

        Scaffold(
            topBar = { MyToolbar() },

        ) {
            Column(modifier = Modifier
                .padding(40.dp)) {
                InputSearch(searchText,
                    onTextChange = {
                        viewModelMain.updateSearchText(it)
                    }, onSearchClicked = {
                        viewModelMain.searchCharacter(it)
                    },
                    onCloseClicked = {
                        viewModelMain.setSearch(false)
                    })
                CharactersList(
                    navController = navController, viewModelMain = viewModelMain,
                    items = if (isSearch) charactersFound else characters
                ) { id ->
                    val route = "detail_screen/${id}"
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            }
        }

        if (!showDialog) {
            Dialog(
                onDismissRequest = { showDialog = true },
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
                Box(

                    modifier = Modifier
                        .size(100.dp)
                        .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center

                ) {
                    ProgressIndicator()

                }

            }
        }
    }
}

@Preview
@Composable
fun MyToolbar() {
    Toolbar()
}
