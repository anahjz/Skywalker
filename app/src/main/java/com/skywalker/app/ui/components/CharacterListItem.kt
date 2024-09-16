package com.skywalker.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skywalker.app.common.getIdFromUrl
import com.skywalker.app.data.model.StarWarsCharacter
import com.skywalker.app.ui.theme.*

@Composable
fun CharacterItem(item: StarWarsCharacter,
    onClick:(id:Int)->Unit
    ) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { onClick(item.urlPeople.getIdFromUrl().toInt())},
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Purple40, Yellow40
                      )
                )
            ),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Column(Modifier.padding(8.dp)) {
                Text(
                    text = item.name, fontSize = 24.sp,
                    style = TextStyle(Color.White)
                )
            }
        }

    }
}