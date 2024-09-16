package com.skywalker.app.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.skywalker.app.ui.components.CharacterDetail
import com.skywalker.app.ui.theme.ComposeAppTheme
import com.skywalker.app.ui.viewModel.ViewModelMain


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    navController: NavController,
    detailId: String,
    viewModelMain: ViewModelMain = hiltViewModel()
) {
    ComposeAppTheme {
        Scaffold() {
            Column {
                CharacterDetail(viewModel = viewModelMain) {
                    navController.navigate("main_screen") {
                        launchSingleTop = true
                    }
                }
                LaunchedEffect(Unit) { viewModelMain.getCharacter(detailId) }

            }
        }
    }
}