package br.com.fiap.reciclatrampo.screens.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.reciclatrampo.model.Coleta
import br.com.fiap.reciclatrampo.ui.theme.AmareloClaro
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ColetaCard(coleta: Coleta) {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = AmareloClaro
        )
    ) {

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = coleta.material,
                style = MaterialTheme.typography.titleMedium
            )

            Text("Peso: ${coleta.pesoEstimado} kg")
            Text("Data: ${coleta.data.format(formatter)}")
            Text("Endereço: ${coleta.endereco}")

            StatusBadge(status = coleta.status)
        }
    }
}