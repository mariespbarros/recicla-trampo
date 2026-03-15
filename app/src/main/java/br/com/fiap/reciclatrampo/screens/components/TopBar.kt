package br.com.fiap.reciclatrampo.screens.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    titulo: String
) {

    CenterAlignedTopAppBar(

        title = {
            Text(
                text = titulo,
                color = Color.White
            )
        },

        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color(0xFF2E7D4F)
        )

    )

}