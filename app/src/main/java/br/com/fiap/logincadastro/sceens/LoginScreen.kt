package br.com.fiap.logincadastro.sceens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.logincadastro.R
import br.com.fiap.logincadastro.data.DatabaseProvider
import br.com.fiap.logincadastro.data.repository.UserRepository
import br.com.fiap.logincadastro.navigation.Routes
import br.com.fiap.logincadastro.viewmodel.UserViewModel
import br.com.fiap.logincadastro.viewmodel.UserViewModelFactory

@Composable
fun LoginScreen(
    navController: NavController
) {

    val erro = remember { mutableStateOf(false) }
    val context = LocalContext.current

    val db = DatabaseProvider.getDatabase(context)
    val repository = UserRepository(db.userDao())

    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(repository)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(R.drawable.eco_login),
            contentDescription = stringResource(R.string.trabalhadores_e_familia_feliz),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(190.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))

        )

        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                OutlinedTextField(
                    value = userViewModel.email.value,
                    onValueChange = { userViewModel.onEmailChange(it) },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = userViewModel.senha.value,
                    onValueChange = { userViewModel.onSenhaChange(it) },
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {

                        val sucesso = userViewModel.login { sucesso ->

                            if (sucesso) {
                                erro.value = false

                                navController.navigate(Routes.ESCOLHA_PERFIL)

                            } else {
                                erro.value = true
                            }
                        }
                    }
                ) {
                    Text("Entrar")
                }

                if (erro.value) {

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Preencha email e senha",
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        navController.navigate(Routes.ESCOLHA_PERFIL)
                    }
                ) {
                    Text("Criar conta")
                }
            }
        }
    }

}