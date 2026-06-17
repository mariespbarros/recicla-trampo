package br.com.fiap.reciclatrampo.ui.theme

import android.graphics.Color
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import br.com.fiap.reciclatrampo.ui.theme.White

private val LightColors = lightColorScheme(
    primary = VerdeClaro,
    secondary = VerdeEscuro,
    onPrimary = White,
    onSecondary = White,
    background = FundoClaro
)

private val DarkColors = darkColorScheme(
    primary = VerdeEscuro,
    secondary = VerdeClaro,
    background = FundoEscuro,
    onPrimary = White,
    onSecondary = White,
    onBackground = White
)

@Composable
fun ReciclaTrampoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}