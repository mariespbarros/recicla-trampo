package br.com.fiap.reciclatrampo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.reciclatrampo.screens.components.BottomBar
import br.com.fiap.reciclatrampo.screens.components.TopBar

// CORES DO APP
val VerdeTopBar = Color(0xFF2E7D4F)
val VerdeBotao = Color(0xFF3F8F63)
val FundoCampo = Color(0xFFE6E3C8)

data class RegistroColetaState(
    val pesoFinal: String = "",
    val observacoes: String = ""
)

@Composable
fun RegistroColetaScreen(
    onFinalizar: () -> Unit
) {

    val formState = remember {
        mutableStateOf(RegistroColetaState())
    }

    Scaffold(

        topBar = {
            TopBar(
                titulo = "Registrar coleta",

                )
        },

        bottomBar = {
            BottomBar(
                selectedIndex = 0,
                onHistoricoClick = {},
                onCriarClick = {}
            )
        }

    ) { paddingValues ->

        RegistroColetaForm(
            state = formState.value,
            onStateChange = { formState.value = it },
            onFinalizarClick = onFinalizar,
            modifier = Modifier.padding(paddingValues)
        )

    }

}

@Composable
fun RegistroColetaForm(
    state: RegistroColetaState,
    onStateChange: (RegistroColetaState) -> Unit,
    onFinalizarClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Registrar coleta"
        )

        // BOTÃO FOTO
        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeBotao
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {

            Icon(
                imageVector = Icons.Default.CameraAlt,
                contentDescription = "Tirar foto",
                tint = Color.White
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                text = "Tirar foto",
                color = Color.White
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Peso final (kg)",
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = state.pesoFinal,
                onValueChange = {
                    onStateChange(
                        state.copy(pesoFinal = it)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(2f)
            )

        }

        // CAMPO OBSERVAÇÃO
        OutlinedTextField(
            value = state.observacoes,
            onValueChange = {
                onStateChange(
                    state.copy(observacoes = it)
                )
            },
            label = {
                Text("Observações")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = FundoCampo,
                unfocusedContainerColor = FundoCampo
            )
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // BOTÃO FINALIZAR
        Button(
            onClick = onFinalizarClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeBotao
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {

            Text(
                text = "Finalizar coleta",
                color = Color.White
            )

        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewRegistroColeta() {

    RegistroColetaScreen(
        onFinalizar = {}
    )

}