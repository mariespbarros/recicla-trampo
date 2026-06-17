package br.com.fiap.reciclatrampo.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.fiap.reciclatrampo.model.ColetaStatus
import br.com.fiap.reciclatrampo.ui.theme.AzulStatus
import br.com.fiap.reciclatrampo.ui.theme.LaranjaStatus
import br.com.fiap.reciclatrampo.ui.theme.VerdeStatus

@Composable
fun corStatus(status: ColetaStatus): Color {
    return when (status) {
        ColetaStatus.SOLICITADA -> LaranjaStatus
        ColetaStatus.ACEITA -> AzulStatus
        ColetaStatus.FINALIZADA -> VerdeStatus
    }
}