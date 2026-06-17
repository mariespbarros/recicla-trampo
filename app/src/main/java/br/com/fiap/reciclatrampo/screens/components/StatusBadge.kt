package br.com.fiap.reciclatrampo.screens.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.fiap.reciclatrampo.model.ColetaStatus

@Composable
fun StatusBadge(status: ColetaStatus) {

    Surface(
        color = corStatus(status),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = status.descricao(),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            style = MaterialTheme.typography.labelMedium,
            color = Color.White
        )
    }
}

