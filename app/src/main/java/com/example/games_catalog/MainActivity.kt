package com.example.games_catalog

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.games_catalog.data.Datasource
import com.example.games_catalog.model.Game
import com.example.games_catalog.ui.theme.GamesCatalogTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // dynamicColor=false — реже проблемы на прошивках без полной поддержки dynamic color
            GamesCatalogTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GamesCatalogScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GameImageSection(game: Game, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        tonalElevation = 2.dp
    ) {
        Image(
            painter = painterResource(game.imageResourceId),
            contentDescription = stringResource(game.titleResourceId),
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun GameDescriptionSection(game: Game, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Text(
            text = stringResource(game.titleResourceId),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(game.descriptionResourceId),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun GameControlPanel(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = onPrevious) {
            Text("Предыдущая")
        }
        Button(onClick = onNext) {
            Text("Следующая")
        }
    }
}

@Composable
fun GamesCatalogScreen(modifier: Modifier = Modifier) {
    val games = remember { Datasource().loadGames() }
    var currentIndex by remember { mutableStateOf(0) }
    val game = games[currentIndex]

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            GameImageSection(game = game)
            Spacer(modifier = Modifier.height(8.dp))
            GameDescriptionSection(game = game)
        }
        GameControlPanel(
            onPrevious = {
                currentIndex = if (currentIndex == 0) games.lastIndex else currentIndex - 1
            },
            onNext = {
                currentIndex = if (currentIndex == games.lastIndex) 0 else currentIndex + 1
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GamesCatalogPreview() {
    GamesCatalogTheme {
        val sample = Game(
            titleResourceId = R.string.game_title_1,
            descriptionResourceId = R.string.game_desc_1,
            imageResourceId = R.drawable.game1
        )
        Column {
            GameImageSection(game = sample)
            GameDescriptionSection(game = sample)
            GameControlPanel(onPrevious = { }, onNext = { })
        }
    }
}
