package br.com.fiap.reciclatrampo.screens.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.reciclatrampo.ui.theme.AmareloClaro
import br.com.fiap.reciclatrampo.ui.theme.AmareloEscuro
import br.com.fiap.reciclatrampo.ui.theme.Black

@Composable
fun FormField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions? = null,
    readOnly: Boolean = false,
    trailingContent: @Composable (() -> Unit)? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            readOnly = readOnly,
            interactionSource = interactionSource,
            keyboardOptions = keyboardOptions ?: KeyboardOptions.Default,
            trailingIcon = trailingContent,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AmareloClaro,
                unfocusedContainerColor = AmareloEscuro,
                focusedTextColor = Black,
                unfocusedTextColor = Black
            )
        )
    }
}