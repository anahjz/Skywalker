package com.skywalker.app.ui.components


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.skywalker.app.R
import com.skywalker.app.data.model.StarWarsCharacter
import com.skywalker.app.ui.theme.Pink80
import com.skywalker.app.ui.theme.Yellow40
import com.skywalker.app.ui.viewModel.ViewModelMain

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CharacterDetail(
    viewModel: ViewModelMain,
    backPress: () -> Unit
) {
    val detail by viewModel.starWarsCharacter.observeAsState(StarWarsCharacter())
    val films by viewModel.filmsLiveData.observeAsState(listOf())
    viewModel.starWarsCharacter.value?.filmsList = films

    var pbIndicatorState by remember { mutableStateOf(false) }

    val painter = rememberImagePainter(data = R.drawable.starwars_placeholder) {
        crossfade(1000)
        error(R.drawable.starwars_placeholder)
        placeholder(R.drawable.starwars_placeholder)

    }
    Scaffold {
        Box(
            modifier = Modifier
                .offset(y = (-150).dp)
                .fillMaxWidth()
                .height(400.dp)
                .clip(
                    shape = RoundedCornerShape(10)
                )
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Yellow40, Pink80)
                    )
                )
        )

        val scrollState = rememberScrollState()
        Column() {
            Row(
                modifier = Modifier
                    .wrapContentSize()

            ) {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (topRef, detailImageRef) = createRefs()
                    TopAppBar(
                        title = {
                            Text(
                                "StarWars",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 64.dp),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic
                            )
                        },
                        modifier = Modifier
                            .background(Color.Transparent)
                            .constrainAs(topRef) {},
                        navigationIcon = {
                            IconButton(onClick = { backPress() }) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_arrow_back),
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        },

                        backgroundColor = Color.Transparent,
                        elevation = 0.dp,

                        )
                    Image(painter = painter,
                        contentDescription = "",
                        modifier = Modifier
                            .size(140.dp, 210.dp)
                            .constrainAs(detailImageRef) {
                                top.linkTo(topRef.bottom, margin = 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })


                }

            }

            Column(
                modifier = Modifier
                    .verticalScroll(
                        scrollState,
                        enabled = true
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp, 0.dp, 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = detail.name,
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold

                        )
                    }
                    CharacterInfo(detail = detail)
                    if (pbIndicatorState) {
                        LinearProgressIndicator(progress = 0.0F, Modifier.fillMaxWidth())
                    } else {
                        LinearProgressIndicator(Modifier.fillMaxWidth())
                    }
                    if (films.isNotEmpty()) {
                        pbIndicatorState = true
                        CharacterMovies(films = films)
                    }
                }


            }

        }
    }
}