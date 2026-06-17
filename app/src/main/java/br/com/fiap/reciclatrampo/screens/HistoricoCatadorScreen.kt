package br.com.fiap.reciclatrampo.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.reciclatrampo.data.SessionManager.catador
import br.com.fiap.reciclatrampo.data.SessionManager.empresa
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
import java.util.Locale

private enum class HistoricoFiltro(val label: String) {
    TODAS("Todas"),
    CONCLUIDAS("Concluídas"),
    EM_ANDAMENTO("Em andamento")
}

@Composable
fun HistoricoCatadorScreen(
    viewModel: ColetaViewModel,
    onBackClick: () -> Unit = {},
    onDetalhesClick: (Coleta) -> Unit = {}
) {
    val catador = catador // SessionManager

    if (catador == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Usuário não logado")
        }
        return
    }

    val coletas by viewModel
        .listarPorCatador(catador.id)
        .collectAsState(initial = emptyList())

    HistoricoCatadorContent(
        coletas = coletas,
        onBackClick = onBackClick,
        onDetalhesClick = onDetalhesClick
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HistoricoCatadorContent(
    coletas: List<Coleta>,
    onBackClick: () -> Unit = {},
    onDetalhesClick: (Coleta) -> Unit = {}
) {
    var filtroSelecionado by remember { mutableStateOf(HistoricoFiltro.TODAS) }

    val coletasFiltradas = remember(coletas, filtroSelecionado) {
        when (filtroSelecionado) {
            HistoricoFiltro.TODAS -> coletas
            HistoricoFiltro.CONCLUIDAS -> coletas.filter { it.status == ColetaStatus.FINALIZADA }
            HistoricoFiltro.EM_ANDAMENTO -> coletas.filter { it.status == ColetaStatus.ACEITA }
        }
    }

    val totalColetas = coletas.size
    val totalMaterial = coletas.sumOf { it.pesoEstimado }

    Scaffold(
        bottomBar = {
            BottomBar(selectedIndex = 1, onHistoricoClick = {}, onCriarClick = {})
        },
        containerColor = Color(0xFFF5F7F9)
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VerdeClaro)
                    .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 24.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Voltar",
                                tint = White
                            )
                        }

                        Text(
                            text = "Meu Histórico",
                            color = White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )

                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Perfil",
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(
                                    text = "Saldo total",
                                    color = White,
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "--",
                                    color = White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 28.sp
                                )
                            }

                            Surface(
                                color = Color(0x33FFFFFF),
                                shape = RoundedCornerShape(20.dp)
                            ) {
                                Text(
                                    text = "$totalColetas coletas",
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCardCatador(
                    value = totalColetas.toString(),
                    label = "Total coletas",
                    modifier = Modifier.weight(1f)
                )
                StatCardCatador(
                    value = formatPeso(totalMaterial),
                    label = "Material",
                    modifier = Modifier.weight(1f)
                )
                StatCardCatador(
                    value = "--",
                    label = "Avaliação",
                    modifier = Modifier.weight(1f),
                    isRating = true
                )
            }

            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HistoricoFiltro.entries.forEach { filtro ->
                    FilterChipCatador(
                        text = filtro.label,
                        selected = filtroSelecionado == filtro,
                        onClick = { filtroSelecionado = filtro }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (coletasFiltradas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Nenhuma coleta no histórico.",
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(coletasFiltradas, key = { it.id }) { coleta ->
                        ColetaHistoricoCatadorCard(
                            coleta = coleta,
                            onDetalhesClick = onDetalhesClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StatCardCatador(
    value: String,
    label: String,
    modifier: Modifier,
    isRating: Boolean = false
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = VerdeClaro
            )
            Text(
                text = label,
                fontSize = 10.sp,
                color = Color.Gray
            )
            if (isRating) {
                Text(
                    text = "⭐ Avaliação",
                    fontSize = 10.sp,
                    color = Orange
                )
            }
        }
    }
}

@Composable
fun ColetaHistoricoCatadorCard(
    coleta: Coleta,
    onDetalhesClick: (Coleta) -> Unit = {}
) {
    val statusColor = when (coleta.status) {
        ColetaStatus.ACEITA -> VerdeStatus
        ColetaStatus.FINALIZADA -> Color(0xFF1976D2)
        else -> Color.Gray
    }

    val statusLabel = when (coleta.status) {
        ColetaStatus.ACEITA -> "Em andamento"
        ColetaStatus.FINALIZADA -> "Concluída"
        else -> "Pendente"
    }

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
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
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Local",
                            tint = VerdeStatus,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = coleta.material,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = if (coleta.endereco.isBlank()) "Endereço não informado" else coleta.endereco,
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Surface(
                        color = statusColor.copy(alpha = 0.1f),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = statusLabel,
                            color = statusColor,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    InfoChipSmallCatador(coleta.hora.format(timeFormatter))
                    InfoChipSmallCatador(formatPeso(coleta.pesoEstimado))
                    InfoChipSmallCatador("R$ --")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { onDetalhesClick(coleta) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF5F5F5)),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Detalhes",
                        color = Color.Gray
                    )
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
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun FilterChipCatador(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        color = if (selected) VerdeStatus else Color(0xFFEEEEEE),
        shape = RoundedCornerShape(if (selected) 20.dp else 8.dp),
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            color = if (selected) White else Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontSize = 12.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

private fun formatPeso(peso: Double): String {
    return if (peso >= 1000.0) {
        String.format(Locale("pt", "BR"), "%.2ft", peso / 1000.0)
    } else {
        String.format(Locale("pt", "BR"), "%.1fkg", peso)
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
        ),
        Coleta(
            id = 2,
            material = "Plástico",
            pesoEstimado = 1280.0,
            endereco = "Rua das Flores, 25",
            data = LocalDate.now(),
            hora = LocalTime.of(16, 30),
            status = ColetaStatus.FINALIZADA,
            empresaId = 2,
            catadorId = 1
        )
    )

    ReciclaTrampoTheme {
        HistoricoCatadorContent(coletas = sampleColetas)
    }
}