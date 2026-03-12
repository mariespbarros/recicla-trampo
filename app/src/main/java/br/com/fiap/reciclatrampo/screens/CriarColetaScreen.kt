package br.com.fiap.reciclatrampo.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.fiap.reciclatrampo.data.AppDatabase
import br.com.fiap.reciclatrampo.data.SessionManager
import br.com.fiap.reciclatrampo.data.repository.ColetaRepository
import br.com.fiap.reciclatrampo.screens.components.BottomBar
import br.com.fiap.reciclatrampo.screens.components.FormField
import br.com.fiap.reciclatrampo.screens.components.TopBar
import br.com.fiap.reciclatrampo.ui.theme.ReciclaTrampoTheme
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModel
import br.com.fiap.reciclatrampo.viewmodel.ColetaViewModelFactory
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class CriarColetaFormState(
    val material: String = "",
    val peso: String = "",
    val data: LocalDate = LocalDate.now(),
    val hora: LocalTime = LocalTime.now()
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CriarColetaScreen(
    coletaViewModel: ColetaViewModel,
    onNavigateToHistorico: () -> Unit,
) {

    //val empresaId = SessionManager.empresaId ?: return
    val empresaId = 1

    var formState by remember {
        mutableStateOf(CriarColetaFormState())
    }

    Scaffold(
        topBar = {
            TopBar(
                titulo = "Solicitar Coleta"
            )
        },

        bottomBar = {
            BottomBar(
                selectedIndex = 1,
                onHistoricoClick = {
                    onNavigateToHistorico()
                },
                onCriarClick = {
                    // já está nessa tela
                }
            )
        }

    ) { paddingValues ->

        CriarColetaForm(
            state = formState,
            onStateChange = { formState = it },
            onSalvarClick = {
                if (
                    formState.material.isNotBlank() &&
                    formState.peso.isNotBlank()
                ) {

                    coletaViewModel.criarColeta(
                        material = formState.material,
                        pesoEstimado = formState.peso.toDoubleOrNull() ?: 0.0,
                        data = formState.data,
                        hora = formState.hora,
                        empresaId = empresaId,
                        endereco = ""
                        //endereco = empresa.endereco
                    )

                    onNavigateToHistorico()
                }
            },
            onCancelarClick = {
                onNavigateToHistorico()
            },
            modifier = Modifier.padding(paddingValues)

        )
    }
}


// FORMULÁRIO DE CRIAR COLETA
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CriarColetaForm(
    state: CriarColetaFormState,
    onStateChange: (CriarColetaFormState) -> Unit,
    onSalvarClick: () -> Unit,
    onCancelarClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        FormField(
            label = "Tipo de material",
            value = state.material,
            onValueChange = {
                onStateChange(state.copy(material = it))
            }
        )

        FormField(
            label = "Volume estimado em Kg",
            value = state.peso,
            onValueChange = {
                onStateChange(state.copy(peso = it))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        val interactionSource = remember { MutableInteractionSource() }

        FormField(
            label = "Data da coleta",
            value = state.data.format(formatter),
            onValueChange = {},
            readOnly = true,
            interactionSource = interactionSource,
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Selecionar data"
                )
            }
        )

        LaunchedEffect(interactionSource) {
            interactionSource.interactions.collect { interaction ->
                if (interaction is PressInteraction.Release) {
                    showDatePicker = true
                }
            }
        }


        val timeInteractionSource = remember { MutableInteractionSource() }

        FormField(
            label = "Hora da coleta",
            value = state.hora.format(timeFormatter),
            onValueChange = {},
            readOnly = true,
            interactionSource = timeInteractionSource,
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Selecionar hora"
                )
            }
        )

        LaunchedEffect(timeInteractionSource) {
            timeInteractionSource.interactions.collect { interaction ->
                if (interaction is PressInteraction.Release) {
                    showTimePicker = true
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedButton(
                onClick = onCancelarClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }

            Button(
                onClick = onSalvarClick,
                modifier = Modifier.weight(1f)
            ) {
                Text("Solicitar")
                Log.d("COLETA_DEBUG", "Botão salvar clicado")
            }
        }

        if (showDatePicker) {

            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = state.data
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli()
            )

            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {

                    Button(
                        onClick = {

                            datePickerState.selectedDateMillis?.let { millis ->

                                val selectedDate = Instant
                                    .ofEpochMilli(datePickerState.selectedDateMillis ?: 0)
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate()

                                onStateChange(
                                    state.copy(data = selectedDate)
                                )
                            }

                            showDatePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {

                    OutlinedButton(
                        onClick = { showDatePicker = false }
                    ) {
                        Text("Cancelar")
                    }
                }
            ) {

                DatePicker(
                    state = datePickerState
                )
            }
        }

        if (showTimePicker) {

            val timePickerState = rememberTimePickerState(
                initialHour = state.hora.hour,
                initialMinute = state.hora.minute,
                is24Hour = true
            )

            AlertDialog(
                onDismissRequest = { showTimePicker = false },

                title = {
                    Text("Selecionar horário")
                },

                text = {
                    TimePicker(
                        state = timePickerState
                    )
                },

                confirmButton = {
                    Button(
                        onClick = {
                            val selectedTime = LocalTime.of(
                                timePickerState.hour,
                                timePickerState.minute
                            )

                            onStateChange(
                                state.copy(hora = selectedTime)
                            )

                            showTimePicker = false
                        }
                    ) {
                        Text("OK")
                    }
                },

                dismissButton = {
                    OutlinedButton(
                        onClick = { showTimePicker = false }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable fun PreviewCriarColetaForm() {
    ReciclaTrampoTheme {
        CriarColetaForm(
            state = CriarColetaFormState(
                material = "Plástico",
                peso = "15",
                data = LocalDate.now(),
                ),
            onStateChange = {},
            onSalvarClick = {},
            onCancelarClick = {},
            modifier = Modifier
        )
    }
}
