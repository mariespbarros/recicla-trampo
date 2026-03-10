package br.com.fiap.reciclatrampo.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.fiap.reciclatrampo.ui.theme.AzulStatus
import br.com.fiap.reciclatrampo.ui.theme.LaranjaStatus
import br.com.fiap.reciclatrampo.ui.theme.VerdeStatus

enum class ColetaStatus {

    SOLICITADA,
    ACEITA,
    FINALIZADA;

    fun descricao(): String {
        return when (this) {
            SOLICITADA -> "Aguardando coletor"
            ACEITA -> "Coletor a caminho"
            FINALIZADA -> "Coleta finalizada"
        }
    }

    @Composable
    fun cor(): Color {
        return when (this) {
            SOLICITADA -> LaranjaStatus
            ACEITA -> AzulStatus
            FINALIZADA -> VerdeStatus
        }
    }
}