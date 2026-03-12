package br.com.fiap.reciclatrampo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.reciclatrampo.data.AppDatabase
import br.com.fiap.reciclatrampo.data.SessionManager
import br.com.fiap.reciclatrampo.data.repository.ColetaRepository
import br.com.fiap.reciclatrampo.screens.CriarColetaScreen
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

            val coletaViewModel: ColetaViewModel =
                viewModel(factory = factory)


            ReciclaTrampoTheme {

                CriarColetaScreen(
                    coletaViewModel = coletaViewModel,
                    onNavigateToHistorico = {},
                )
            }
        }
    }
}