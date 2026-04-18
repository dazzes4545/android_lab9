package com.example.games_catalog.data

import com.example.games_catalog.R
import com.example.games_catalog.model.Game

class Datasource {
    fun loadGames(): List<Game> {
        return listOf(
            Game(
                titleResourceId = R.string.game_title_1,
                descriptionResourceId = R.string.game_desc_1,
                imageResourceId = R.drawable.game1
            ),
            Game(
                titleResourceId = R.string.game_title_2,
                descriptionResourceId = R.string.game_desc_2,
                imageResourceId = R.drawable.game2
            ),
            Game(
                titleResourceId = R.string.game_title_3,
                descriptionResourceId = R.string.game_desc_3,
                imageResourceId = R.drawable.game3
            ),
            Game(
                titleResourceId = R.string.game_title_4,
                descriptionResourceId = R.string.game_desc_4,
                imageResourceId = R.drawable.game4
            ),
            Game(
                titleResourceId = R.string.game_title_5,
                descriptionResourceId = R.string.game_desc_5,
                imageResourceId = R.drawable.game5
            )
        )
    }
}
