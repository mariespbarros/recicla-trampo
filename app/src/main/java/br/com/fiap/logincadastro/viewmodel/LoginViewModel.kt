package br.com.fiap.logincadastro.viewmodel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email = mutableStateOf("")
    var senha = mutableStateOf("")

    fun onEmailChange(novoEmail: String) {
        email.value = novoEmail
    }

    fun onSenhaChange(novaSenha: String) {
        senha.value = novaSenha
    }

    fun fazerLogin(): Boolean {

        if (email.value.isEmpty()) return false
        if (senha.value.isEmpty()) return false

        return true
    }

}
