package br.com.fiap.reciclatrampo.screens.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    selectedIndex: Int,
    onHistoricoClick: () -> Unit,
    onCriarClick: () -> Unit
) {

    NavigationBar(
        containerColor = Color(0xFF2E7D4F)
    ) {

        NavigationBarItem(
            selected = selectedIndex == 0,
            onClick = { onHistoricoClick() },
            icon = {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "Histórico",
                    tint = Color.White
                )
            }
        )

        NavigationBarItem(
            selected = selectedIndex == 1,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White
                )
            }
        )

        NavigationBarItem(
            selected = selectedIndex == 2,
            onClick = { onCriarClick() },
            icon = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Criar",
                    tint = Color.White
                )
            }
        )

    }

}