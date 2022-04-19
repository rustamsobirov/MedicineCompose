package me.ruyeo.medicinecompose.ui.screen

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.ruyeo.medicinecompose.R
import me.ruyeo.medicinecompose.component.BottomMenuItem
import me.ruyeo.medicinecompose.component.FeatureItem
import me.ruyeo.medicinecompose.ui.model.BottomMenuContent
import me.ruyeo.medicinecompose.ui.model.Feature
import me.ruyeo.medicinecompose.ui.theme.*

@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBlue)
    ) {
        Column() {
            FeatureSection(
                feature = listOf(
                    Feature(
                        "Sleep Meditation",
                        R.drawable.ic_baseline_search_24,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        "Tips for Sleeping",
                        R.drawable.ic_baseline_search_24,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        "Hello",
                        R.drawable.ic_baseline_search_24,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        "Hello",
                        R.drawable.ic_baseline_search_24,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    )
                )
            )
        }
        BottomMenu(
            items = listOf(
                BottomMenuContent("Home", R.drawable.ic_baseline_search_24),
                BottomMenuContent("Meditate", R.drawable.ic_baseline_play_arrow_24),
                BottomMenuContent("Sleep", R.drawable.ic_baseline_search_24),
                BottomMenuContent("Music", R.drawable.ic_baseline_play_arrow_24),
                BottomMenuContent("Profile", R.drawable.ic_baseline_search_24)
            ), modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun BottomMenu(
    items: List<BottomMenuContent>,
    modifier: Modifier = Modifier,
    activeHighlightColor: Color = ButtonBlue,
    activeTextColor: Color = Color.White,
    inactiveTextColor: Color = AquaBlue,
    initialSelectedItemIndex: Int = 0
) {
    var selectedItemIndex by remember {
        mutableStateOf(initialSelectedItemIndex)
    }
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(DeepBlue)
            .padding(15.dp)
    ) {
        items.forEachIndexed { index, item ->
            BottomMenuItem(
                item = item,
                isSelected = index == selectedItemIndex,
                activeHighlightColor = activeHighlightColor,
                activeTextColor = activeTextColor,
                inactiveTextColor = inactiveTextColor
            ) {
                selectedItemIndex = index
            }
        }
    }
}

@Composable
fun GreetingSection(
    name: String = "Rustam"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "Good Morning $name",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Text(
                text = "We wish you have a good day",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_search_24),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }

}

@Composable
fun ChipSection(
    chips: List<String>
) {
    var selectionChipIndex by remember { mutableStateOf(0) }

    LazyRow() {
        items(chips.size) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                .clickable {
                    selectionChipIndex = it
                }
                .clip(RoundedCornerShape(12.dp))
                .background(if (selectionChipIndex == it) ButtonBlue else DarkerButtonBlue)
                .padding(16.dp)
            ) {
                Text(
                    text = chips[it],
                    color = TextWhite
                )
            }
        }
    }
}

@Composable
fun CurrentMeditation(
    color: Color = Color.Red
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column() {
            Text(
                text = "Daily Thought",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            Text(
                text = "Meditation * 3-10 min",
                style = MaterialTheme.typography.body1,
                color = Color.White
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(ButtonBlue)
                .padding(10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_play_arrow_24),
                contentDescription = "Play"
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FeatureSection(
    feature: List<Feature>
) {
    Column(Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            item {
                GreetingSection()
            }
            item {
                ChipSection(chips = listOf("Sweet Sleep", "Insomnia", "Depression"))
            }
            item {
                CurrentMeditation()
            }
            item {
                Text(
                    text = "Features",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 100.dp)
        ) {
            items(feature.size) {
                FeatureItem(feature = feature[it])
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}