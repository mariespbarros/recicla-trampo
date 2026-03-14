package br.com.fiap.reciclatrampo.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun HistoricoEmpresaScreen(
    viewModel: ColetaViewModel,
    onBackClick: () -> Unit = {},
    onCriarClick: () -> Unit = {}
) {
    val empresaId = 1
    val coletas by viewModel.listarPorEmpresa(empresaId).collectAsState(initial = emptyList())

    HistoricoEmpresaContent(
        coletas = coletas,
        onBackClick = onBackClick,
        onCriarClick = onCriarClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricoEmpresaContent(
    coletas: List<Coleta>,
    onBackClick: () -> Unit = {},
    onCriarClick: () -> Unit = {}
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = 0,
                onHistoricoClick = {},
                onCriarClick = onCriarClick
            )
        },
        containerColor = Color(0xFFF5F7F9)
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            // Header
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
                        Text(
                            "Histórico de Coletas",
                            color = White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Surface(
                            color = Color(0x33FFFFFF),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                "Empresa",
                                color = White,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            modifier = Modifier.size(60.dp),
                            color = Color(0xFF90CAF9),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = null,
                                tint = White,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                "Supermercados Bom Preço",
                                color = White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                "CNPJ: 12.345.678/0001-90",
                                color = White.copy(alpha = 0.8f),
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }

            // Estatísticas
            Row(
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatCardEmpresa("${coletas.size}", "Coletas", Modifier.weight(1f))
                StatCardEmpresa("${coletas.sumOf { it.pesoEstimado }}kg", "Material", Modifier.weight(1f))
                StatCardEmpresa("R$ 0", "Economia", Modifier.weight(1f))
                StatCardEmpresa("5.0", "Média", Modifier.weight(1f), isRating = true)
            }

            // Filtros de Status
            Row(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChipEmpresaSelected("Todas")
                FilterChipEmpresaUnselected("Pendentes")
                FilterChipEmpresaUnselected("Concluídas")
                FilterChipEmpresaUnselected("Canceladas")
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Filtros de Tempo
            Row(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChipTimeSelected("Últimos 30 dias")
                FilterChipTimeUnselected("Este mês")
                FilterChipTimeUnselected("Mês passado")
            }

            // Lista
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("⌛ Histórico Recente", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.weight(1f))
                Surface(color = VerdeStatus, shape = RoundedCornerShape(12.dp)) {
                    Text(
                        "${coletas.size} coletas",
                        color = White,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 12.sp
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(coletas.size) { index ->
                    ColetaHistoricoEmpresaCard(coletas[index])
                }
            }
        }
    }
}

@Composable
fun StatCardEmpresa(value: String, label: String, modifier: Modifier, isRating: Boolean = false) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = VerdeClaro)
            Text(label, fontSize = 10.sp, color = Color.Gray)
            if (isRating) {
                Text("⭐", fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun FilterChipEmpresaSelected(text: String) {
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
fun FilterChipEmpresaUnselected(text: String) {
    Text(
        text,
        color = Color.Gray,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
        fontSize = 12.sp
    )
}

@Composable
fun FilterChipTimeSelected(text: String) {
    Surface(
        color = VerdeStatus,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text,
            color = White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 12.sp
        )
    }
}

@Composable
fun FilterChipTimeUnselected(text: String) {
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

@Composable
fun ColetaHistoricoEmpresaCard(coleta: Coleta) {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = White),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Build, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(18.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("COL-${coleta.id}", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    color = when(coleta.status) {
                        ColetaStatus.SOLICITADA -> Color(0xFFFFF3E0)
                        ColetaStatus.FINALIZADA -> Color(0xFFE8F5E9)
                        else -> Color(0xFFE3F2FD)
                    },
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        coleta.status.name,
                        color = when(coleta.status) {
                            ColetaStatus.SOLICITADA -> Color(0xFFE65100)
                            ColetaStatus.FINALIZADA -> VerdeStatus
                            else -> Color(0xFF1976D2)
                        },
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        fontSize = 10.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                InfoChipSmallEmpresa(coleta.hora.format(timeFormatter))
                InfoChipSmallEmpresa("${coleta.pesoEstimado}kg")
                InfoChipSmallEmpresa(coleta.data.format(dateFormatter))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(color = Color(0xFFE8EAF6), shape = RoundedCornerShape(8.dp)) {
                    Text(coleta.material, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), fontSize = 10.sp, color = Color(0xFF3F51B5))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (coleta.catadorId != null) {
                Surface(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = null,
                            tint = VerdeStatus,
                            modifier = Modifier.size(32.dp).clip(CircleShape).background(Color.White).padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text("João Silva ✓ Verificado", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            Text("⭐ 4.9 - 127 coletas", fontSize = 10.sp, color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedButton(
                            onClick = {},
                            contentPadding = PaddingValues(horizontal = 12.dp),
                            modifier = Modifier.height(32.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Contato", fontSize = 10.sp)
                        }
                    }
                }
            } else {
                Text(
                    "Aguardando catador...",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.weight(1f).height(40.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Icon(Icons.Default.Place, contentDescription = null, tint = VerdeStatus, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Endereço", color = VerdeStatus)
                }
                Button(
                    onClick = { },
                    modifier = Modifier.weight(1f).height(40.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeClaro)
                ) {
                    Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Detalhes")
                }
            }
        }
    }
}

@Composable
fun InfoChipSmallEmpresa(text: String) {
    Surface(
        color = Color(0xFFF5F5F5),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoricoEmpresaPreview() {
    val coletasExemplo = listOf(
        Coleta(
            id = 1,
            material = "Papelão",
            pesoEstimado = 50.0,
            endereco = "Rua Exemplo, 123",
            data = LocalDate.now(),
            hora = LocalTime.now(),
            status = ColetaStatus.SOLICITADA,
            empresaId = 1
        ),
        Coleta(
            id = 2,
            material = "Plástico",
            pesoEstimado = 25.5,
            endereco = "Av. Paulista, 1000",
            data = LocalDate.now(),
            hora = LocalTime.now(),
            status = ColetaStatus.FINALIZADA,
            empresaId = 1,
            catadorId = 10
        )
    )

    ReciclaTrampoTheme {
        HistoricoEmpresaContent(coletas = coletasExemplo)
    }
}
