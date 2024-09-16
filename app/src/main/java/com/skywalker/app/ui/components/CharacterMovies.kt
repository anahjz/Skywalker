package com.skywalker.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skywalker.app.data.model.Film
import com.skywalker.app.ui.theme.PurpleGrey40
import com.skywalker.app.ui.theme.Yellow40

@Composable
fun CharacterMovies(films: List<Film>) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row() {
            Text(
                text = "Movies", fontWeight = FontWeight.Bold, fontSize = 22.sp,
            )

        }
    }
    LazyRow(
        Modifier.wrapContentHeight()
    ) {
        items(films) { film ->
            Card(
                Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.medium,
                elevation = 4.dp,
                backgroundColor = MaterialTheme.colors.surface
            ) {
                Row(
                    Modifier.background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                PurpleGrey40, Yellow40
                            )
                        )
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(Modifier.padding(8.dp)) {
                        Text(
                            text = film.title,
                            fontSize = 24.sp,
                            style = TextStyle(color = Color.White)
                        )
                        Row() {
                            Text(text = "ReleaseDate ", style = TextStyle(color = Color.White))
                            Text(
                                text = film.releaseDate,
                                style = TextStyle(color = Color.White)
                            )

                        }
                    }
                }
            }
        }
    }
}
