package br.com.fiap.reciclatrampo.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.reciclatrampo.ReciclaCatadorFlow
import br.com.fiap.reciclatrampo.ReciclaEmpresaFlow
import br.com.fiap.reciclatrampo.data.auth.DatabaseProvider
import br.com.fiap.reciclatrampo.data.auth.repository.UserRepository
import br.com.fiap.reciclatrampo.screens.auth.CadastroCatadorScreen
import br.com.fiap.reciclatrampo.screens.auth.CadastroEmpresaScreen
import br.com.fiap.reciclatrampo.screens.auth.CadastroScreen
import br.com.fiap.reciclatrampo.screens.auth.EscolhaPerfilScreen
import br.com.fiap.reciclatrampo.screens.auth.LoginScreen
import br.com.fiap.reciclatrampo.viewmodel.auth.UserViewModel
import br.com.fiap.reciclatrampo.viewmodel.auth.UserViewModelFactory

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ReciclaTrampoApp(navController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    val userRepository = UserRepository(DatabaseProvider.getDatabase(context).userDao())
    val userViewModel: UserViewModel = viewModel(
        factory = UserViewModelFactory(userRepository)
    )

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                navController = navController,
                userViewModel = userViewModel,
                onLoginSuccess = { tipo ->
                    val destino = if (tipo == "empresa") Routes.APP_EMPRESA else Routes.APP_CATADOR
                    navController.navigate(destino) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.CADASTRO) {
            CadastroScreen(navController)
        }

        composable(Routes.ESCOLHA_PERFIL) {
            EscolhaPerfilScreen(navController)
        }

        composable(Routes.CADASTRO_CATADOR) {
            CadastroCatadorScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(Routes.CADASTRO_EMPRESA) {
            CadastroEmpresaScreen(
                navController = navController,
                userViewModel = userViewModel
            )
        }

        composable(Routes.APP_EMPRESA) {
            ReciclaEmpresaFlow(
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.APP_CATADOR) {
            ReciclaCatadorFlow(
                onLogout = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}
