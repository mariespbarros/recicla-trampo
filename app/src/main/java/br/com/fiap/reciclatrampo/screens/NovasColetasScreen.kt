package br.com.fiap.reciclatrampo.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
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
import br.com.fiap.reciclatrampo.ui.theme.ReciclaTrampoTheme
import br.com.fiap.reciclatrampo.ui.theme.VerdeClaro
import br.com.fiap.reciclatrampo.ui.theme.VerdeStatus
import br.com.fiap.reciclatrampo.ui.theme.White
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun NovasColetasScreen(
    viewModel: ColetaViewModel,
    onBackClick: () -> Unit = {},
    onNavigateToHistoricoCatador: () -> Unit = {}
) {
    val coletas by viewModel.listarDisponiveis().collectAsState(initial = emptyList())
    
    // Simulação de ID do catador logado (poderia vir do SessionManager)
    val catadorId = 1

    NovasColetasContent(
        coletas = coletas,
        onBackClick = onBackClick,
        onAceitarClick = { coleta ->
            viewModel.aceitarColeta(coleta, catadorId)
            onNavigateToHistoricoCatador()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NovasColetasContent(
    coletas: List<Coleta>,
    onBackClick: () -> Unit = {},
    onAceitarClick: (Coleta) -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Novas Coletas", color = White, fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VerdeClaro
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Notifications, contentDescription = null, tint = White)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                            tint = White
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomBar(
                selectedIndex = 0,
                onHistoricoClick = {},
                onCriarClick = {}
            )
        },
        containerColor = Color(0xFFF5F7F9)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Pedidos nas proximidades",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            if (coletas.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhuma nova coleta disponível.")
                }
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(coletas) { coleta ->
                        PedidoColetaCard(
                            coleta = coleta,
                            onAceitarClick = { onAceitarClick(coleta) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PedidoColetaCard(
    coleta: Coleta,
    onAceitarClick: () -> Unit = {}
) {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = coleta.material, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(
                        text = "${coleta.data.format(dateFormatter)} às ${coleta.hora.format(timeFormatter)}",
                        color = VerdeStatus,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                }
                Surface(
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "${coleta.pesoEstimado} Kg",
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        color = VerdeStatus,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                Text(
                    text = if (coleta.endereco.isBlank()) "Endereço não informado" else coleta.endereco,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }

            Row(modifier = Modifier.padding(vertical = 12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        coleta.material, 
                        fontSize = 12.sp, 
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.Gray
                    )
                }
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Recusar")
                }
                Button(
                    onClick = onAceitarClick,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeClaro)
                ) {
                    Text("Aceitar")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NovasColetasPreview() {
    val sampleColetas = listOf(
        Coleta(
            id = 1,
            material = "Papelão",
            pesoEstimado = 45.0,
            endereco = "Av. Paulista, 1000",
            data = LocalDate.now(),
            hora = LocalTime.of(14, 0),
            status = ColetaStatus.SOLICITADA,
            empresaId = 1
        ),
        Coleta(
            id = 2,
            material = "Vidro",
            pesoEstimado = 12.5,
            endereco = "Rua Augusta, 500",
            data = LocalDate.now(),
            hora = LocalTime.of(16, 30),
            status = ColetaStatus.SOLICITADA,
            empresaId = 1
        )
    )

    ReciclaTrampoTheme {
        NovasColetasContent(coletas = sampleColetas)
    }
}
