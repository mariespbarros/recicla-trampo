package br.com.fiap.reciclatrampo.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.reciclatrampo.navigation.Routes
import br.com.fiap.reciclatrampo.viewmodel.auth.UserViewModel

@Composable
fun CadastroEmpresaScreen(
    navController: NavController,
    userViewModel: UserViewModel
) {
    val senhaVisivel = remember { mutableStateOf(false) }
    val erro = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Business,
            contentDescription = null,
            modifier = Modifier.height(56.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Cadastro Empresa",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(20.dp)) {
                OutlinedTextField(
                    value = userViewModel.nomeEmpresa.value,
                    onValueChange = { userViewModel.onNomeEmpresaChange(it) },
                    label = { Text("Nome da Empresa") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = userViewModel.cnpj.value,
                    onValueChange = { userViewModel.onCnpjChange(it) },
                    label = { Text("CNPJ") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = userViewModel.endereco.value,
                    onValueChange = { userViewModel.onEnderecoChange(it) },
                    label = { Text("Endereço") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = userViewModel.email.value,
                    onValueChange = { userViewModel.onEmailChange(it) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = userViewModel.senha.value,
                    onValueChange = { userViewModel.onSenhaChange(it) },
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (senhaVisivel.value) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { senhaVisivel.value = !senhaVisivel.value }) {
                            Icon(
                                imageVector = if (senhaVisivel.value) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                contentDescription = null
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        userViewModel.cadastrarEmpresa { sucesso, mensagem ->
                            if (sucesso) {
                                erro.value = null
                                navController.navigate(Routes.LOGIN) {
                                    popUpTo(Routes.LOGIN) { inclusive = true }
                                }
                            } else {
                                erro.value = mensagem ?: "Erro ao cadastrar"
                            }
                        }
                    }
                ) {
                    Text("Cadastrar")
                }

                erro.value?.let { mensagem ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = mensagem,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { navController.popBackStack() }
                ) {
                    Text("Voltar")
                }
            }
        }
    }
}
