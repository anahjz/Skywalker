package com.skywalker.app.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.skywalker.app.ui.theme.Purple80

@Preview
@Composable
fun ProgressIndicator() {
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            Modifier.padding(16.dp, 8.dp, 16.dp, 4.dp),
            color = Purple80,
            strokeWidth = Dp(value = 4F),
        )
        Text(text = "Loading", Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp))

    }
}