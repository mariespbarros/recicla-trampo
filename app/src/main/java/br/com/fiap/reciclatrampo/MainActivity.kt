package br.com.fiap.reciclatrampo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.reciclatrampo.data.AppDatabase
import br.com.fiap.reciclatrampo.data.repository.ColetaRepository
import br.com.fiap.reciclatrampo.screens.CriarColetaScreen
import br.com.fiap.reciclatrampo.screens.HistoricoEmpresaScreen
import br.com.fiap.reciclatrampo.screens.NovasColetasScreen // Adicionado
import br.com.fiap.reciclatrampo.screens.HistoricoCatadorScreen // Adicionado
import br.com.fiap.reciclatrampo.ui.theme.ReciclaTrampoTheme
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModel
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModelFactory

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val repository = ColetaRepository(database.coletaDao())
        val factory = ColetaViewModelFactory(repository)

        setContent {
            val coletaViewModel: ColetaViewModel = viewModel(factory = factory)

            // Altere para "novas_coletas" para iniciar diretamente nela
            var currentScreen by remember { mutableStateOf("novas_coletas") }

            ReciclaTrampoTheme {
                when (currentScreen) {
                    "criar_coleta" -> {
                        CriarColetaScreen(
                            coletaViewModel = coletaViewModel,
                            onNavigateToHistorico = { currentScreen = "historico" },
                        )
                    }
                    "historico" -> {
                        HistoricoEmpresaScreen(
                            viewModel = coletaViewModel,
                            onBackClick = { currentScreen = "criar_coleta" },
                            onCriarClick = { currentScreen = "criar_coleta" }
                        )
                    }
                    "novas_coletas" -> {
                        NovasColetasScreen(
                            viewModel = coletaViewModel,
                            onBackClick = { currentScreen = "criar_coleta" },
                            onNavigateToHistoricoCatador = { currentScreen = "historico_catador" }
                        )
                    }
                    "historico_catador" -> {
                        HistoricoCatadorScreen(
                            viewModel = coletaViewModel,
                            onBackClick = { currentScreen = "novas_coletas" }
                        )
                    }
                }
            }
        }
    }
}