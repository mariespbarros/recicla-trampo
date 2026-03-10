package br.com.fiap.reciclatrampo.screens.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector
)


@Composable
fun BottomBar(
    selectedIndex: Int,
    onHistoricoClick: () -> Unit,
    onCriarClick: () -> Unit
) {

    val items = listOf(
        BottomNavigationItem("Home", Icons.Default.Home),
        BottomNavigationItem("Create Coleta", Icons.Default.Create)
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary
    ) {

        items.forEachIndexed { index, item ->

            NavigationBarItem(
                selected = selectedIndex == index,

                onClick = {
                    if (index == 0) onHistoricoClick()
                    if (index == 1) onCriarClick()
                },

                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(28.dp)
                    )
                }
            )
        }
    }
}