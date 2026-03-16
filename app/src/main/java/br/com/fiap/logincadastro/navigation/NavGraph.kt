package br.com.fiap.logincadastro.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.logincadastro.sceens.CadastroCatadorScreen
import br.com.fiap.logincadastro.sceens.CadastroEmpresaScreen
import br.com.fiap.logincadastro.sceens.CadastroScreen
import br.com.fiap.logincadastro.sceens.EscolhaPerfilScreen
import br.com.fiap.logincadastro.sceens.LoginScreen


@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.CADASTRO) {
            CadastroScreen(navController)
        }

        composable(Routes.ESCOLHA_PERFIL) {
            EscolhaPerfilScreen(navController)
        }

        composable(Routes.CADASTRO_CATADOR) {
            CadastroCatadorScreen(navController)
        }

        composable(Routes.CADASTRO_EMPRESA) {
            CadastroEmpresaScreen(navController)
        }
    }

}