package br.com.fiap.reciclatrampo

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import br.com.fiap.reciclatrampo.navigation.ReciclaTrampoApp
import br.com.fiap.reciclatrampo.ui.theme.ReciclaTrampoTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReciclaTrampoTheme {
                ReciclaTrampoApp()
            }
        }
    }
}
