package br.com.fiap.reciclatrampo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.reciclatrampo.data.repository.ColetaRepository
import br.com.fiap.reciclatrampo.screens.CriarColetaScreen
import br.com.fiap.reciclatrampo.screens.HistoricoCatadorScreen
import br.com.fiap.reciclatrampo.screens.HistoricoEmpresaScreen
import br.com.fiap.reciclatrampo.screens.NovasColetasScreen
import br.com.fiap.reciclatrampo.screens.RegistroColetaScreen
import br.com.fiap.reciclatrampo.ui.theme.ReciclaTrampoTheme
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModel
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModelFactory
import androidx.compose.ui.platform.LocalContext
import br.com.fiap.reciclatrampo.data.auth.AppDatabase

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReciclaEmpresaFlow(onLogout: () -> Unit = {}) {
    val context = LocalContext.current
    val factory = remember {
        ColetaViewModelFactory(
            ColetaRepository(AppDatabase.getDatabase(context).coletaDao())
        )
    }
    val coletaViewModel: ColetaViewModel = viewModel(factory = factory)
    var currentScreen by remember { mutableStateOf("criar_coleta") }

    ReciclaTrampoTheme {
        when (currentScreen) {
            "criar_coleta" -> CriarColetaScreen(
                coletaViewModel = coletaViewModel,
                onNavigateToHistorico = { currentScreen = "historico" }
            )

            "historico" -> HistoricoEmpresaScreen(
                viewModel = coletaViewModel,
                onBackClick = { currentScreen = "criar_coleta" },
                onCriarClick = { currentScreen = "criar_coleta" }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReciclaCatadorFlow(onLogout: () -> Unit = {}) {
    val context = LocalContext.current
    val factory = remember {
        ColetaViewModelFactory(
            ColetaRepository(AppDatabase.getDatabase(context).coletaDao())
        )
    }
    val coletaViewModel: ColetaViewModel = viewModel(factory = factory)
    var currentScreen by remember { mutableStateOf("novas_coletas") }

    ReciclaTrampoTheme {
        when (currentScreen) {
            "novas_coletas" -> NovasColetasScreen(
                viewModel = coletaViewModel,
                onBackClick = { currentScreen = "historico_catador" },
                onNavigateToHistoricoCatador = { currentScreen = "historico_catador" },
                onNavigateToRegistro = { currentScreen = "registro_coleta" }
            )

            "registro_coleta" -> RegistroColetaScreen(
                onFinalizar = { currentScreen = "historico_catador" }
            )

            "historico_catador" -> HistoricoCatadorScreen(
                viewModel = coletaViewModel,
                onBackClick = { currentScreen = "novas_coletas" },
            )
        }
    }
}
