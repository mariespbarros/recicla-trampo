package br.com.fiap.reciclatrampo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.reciclatrampo.data.AppDatabase
import br.com.fiap.reciclatrampo.data.repository.ColetaRepository
import br.com.fiap.reciclatrampo.screens.CriarColetaScreen
import br.com.fiap.reciclatrampo.ui.theme.ReciclaTrampoTheme
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModel
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModelFactory

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // database
        val database = AppDatabase.getDatabase(this)

        // repository
        val repository = ColetaRepository(database.coletaDao())

        // viewmodel usando factory
        val viewModel = ViewModelProvider(
            this,
            ColetaViewModelFactory(repository)
        )[ColetaViewModel::class.java]

        setContent {
            ReciclaTrampoTheme {

                CriarColetaScreen(
                    empresaId = 1,
                    coletaViewModel = viewModel,
                    onNavigateToHistorico = {
                        // navegação aqui depois
                    },
                )

            }
        }
    }
}