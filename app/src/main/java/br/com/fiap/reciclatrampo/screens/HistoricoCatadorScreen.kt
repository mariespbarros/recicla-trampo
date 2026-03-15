package br.com.fiap.reciclatrampo.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.reciclatrampo.model.Coleta
import br.com.fiap.reciclatrampo.model.ColetaStatus
import br.com.fiap.reciclatrampo.screens.components.BottomBar
import br.com.fiap.reciclatrampo.ui.theme.Orange
import br.com.fiap.reciclatrampo.ui.theme.ReciclaTrampoTheme
import br.com.fiap.reciclatrampo.ui.theme.VerdeClaro
import br.com.fiap.reciclatrampo.ui.theme.VerdeStatus
import br.com.fiap.reciclatrampo.ui.theme.White
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun HistoricoCatadorScreen(
    viewModel: ColetaViewModel,
    onBackClick: () -> Unit = {}
) {
    // Simulação de ID do catador logado (deve ser o mesmo usado ao aceitar)
    val catadorId = 1
    val coletas by viewModel.listarPorCatador(catadorId).collectAsState(initial = emptyList())

    HistoricoCatadorContent(
        coletas = coletas,
        onBackClick = onBackClick
    )
}

@Composable
fun HistoricoCatadorContent(
    coletas: List<Coleta>,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomBar(selectedIndex = 1, onHistoricoClick = {}, onCriarClick = {})
        },
        containerColor = Color(0xFFF5F7F9)
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VerdeClaro)
                    .padding(top = 16.dp, bottom = 24.dp, start = 16.dp, end = 16.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBackClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = White)
                        }
                        Text("Meu Histórico", color = White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Icon(
                            Icons.Default.AccountCircle,
                            contentDescription = null,
                            tint = White,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0x33FFFFFF)),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text("Saldo total", color = White, fontSize = 14.sp)
                                Text("R$ 4.850,00", color = White, fontWeight = FontWeight.Bold, fontSize = 28.sp)
                            }
                            Surface(
                                color = Color(0x33FFFFFF),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(
                                    "${coletas.size} coletas",
                                    color = White,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }

            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCardCatador("${coletas.size}", "Total coletas", Modifier.weight(1f))
                StatCardCatador("3.45t", "Material", Modifier.weight(1f))
                StatCardCatador("4.8", "Avaliação", Modifier.weight(1f), isRating = true)
            }

            Row(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChipCatadorSelected("Todas")
                FilterChipCatadorTimeUnselected("Concluídas")
                FilterChipCatadorTimeUnselected("Em andamento")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (coletas.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhuma coleta no histórico.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(coletas) { coleta ->
                        ColetaHistoricoCatadorCard(coleta)
                    }
                }
            }
        }
    }
}

@Composable
fun StatCardCatador(value: String, label: String, modifier: Modifier, isRating: Boolean = false) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = VerdeClaro)
            Text(label, fontSize = 10.sp, color = Color.Gray)
            if (isRating) {
                Text("⭐ Avaliação", fontSize = 10.sp, color = Orange)
            }
        }
    }
}

@Composable
fun ColetaHistoricoCatadorCard(coleta: Coleta) {
    val statusColor = when(coleta.status) {
        ColetaStatus.ACEITA -> VerdeStatus
        ColetaStatus.FINALIZADA -> Color.Blue
        else -> Color.Gray
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .fillMaxHeight()
                    .background(statusColor)
            )
            
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = VerdeStatus,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(coleta.material, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                        Text(if(coleta.endereco.isBlank()) "Endereço não informado" else coleta.endereco, fontSize = 12.sp, color = Color.Gray)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Surface(
                        color = statusColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            coleta.status.name,
                            color = statusColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
                    InfoChipSmallCatador(coleta.hora.format(timeFormatter))
                    InfoChipSmallCatador("${coleta.pesoEstimado}kg")
                    InfoChipSmallCatador("R$ --") // Valor ainda não definido no modelo
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Detalhes", color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun InfoChipSmallCatador(text: String) {
    Surface(
        color = Color(0xFFF5F5F7),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FilterChipCatadorSelected(text: String) {
    Surface(
        color = VerdeStatus,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text,
            color = White,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FilterChipCatadorTimeUnselected(text: String) {
    Surface(
        color = Color(0xFFEEEEEE),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 12.sp
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoricoCatadorPreview() {
    val sampleColetas = listOf(
        Coleta(
            id = 1,
            material = "Papelão",
            pesoEstimado = 45.0,
            endereco = "Av. Paulista, 1000",
            data = LocalDate.now(),
            hora = LocalTime.of(14, 0),
            status = ColetaStatus.ACEITA,
            empresaId = 1,
            catadorId = 1
        )
    )
    ReciclaTrampoTheme {
        HistoricoCatadorContent(coletas = sampleColetas)
    }
}
