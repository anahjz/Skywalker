package com.skywalker.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.skywalker.app.data.model.StarWarsCharacter


@Composable
fun CharacterInfo(
    detail: StarWarsCharacter
) {
    ConstraintLayout(modifier = Modifier.wrapContentSize()) {
        val (textBirth, textGender, textHome, textHeight, textSpecie) = createRefs()

        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textBirth) {
                    top.linkTo(parent.top, margin = 8.dp)
                    start.linkTo(parent.start, margin = 8.dp)
                }) {
            Column() {
                Text(text = "Birth Year", fontWeight = FontWeight.Bold)
                Text(text = detail.birthYear)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textGender) {
                    top.linkTo(textBirth.bottom, margin = 8.dp)
                    start.linkTo(textBirth.start)
                }) {
            Column() {
                Text(text = "Gender", fontWeight = FontWeight.Bold)
                Text(text = detail.gender)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textHome) {
                    top.linkTo(textGender.bottom, margin = 8.dp)
                    start.linkTo(textGender.start)
                }) {
            Column() {
                Text(text = "home", fontWeight = FontWeight.Bold)
                Text(text = detail.home)
            }

        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textHeight) {
                    top.linkTo(parent.top, margin = 8.dp)
                    end.linkTo(parent.end, margin = 8.dp)
                    start.linkTo(textBirth.end, margin = 8.dp)
                }) {
            Column() {
                Text(text = "height", fontWeight = FontWeight.Bold)
                Text(text = detail.height)
            }
        }
        Row(
            Modifier
                .padding(4.dp)
                .constrainAs(textSpecie) {
                    top.linkTo(textHeight.bottom, margin = 8.dp)
                    start.linkTo(textHeight.start)

                }) {
            Column() {
                Text(text = "Specie", fontWeight = FontWeight.Bold)
                Text(text = detail.specie.ifEmpty { "n/a" })
            }
        }
    }
}