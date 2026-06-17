package br.com.fiap.reciclatrampo.viewmodel.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.reciclatrampo.data.SessionManager
import br.com.fiap.reciclatrampo.data.auth.UserEntity
import br.com.fiap.reciclatrampo.data.auth.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: MutableState<String> = mutableStateOf("")
        private set

    var senha: MutableState<String> = mutableStateOf("")
        private set

    fun onEmailChange(novoEmail: String) {
        email.value = novoEmail
    }

    fun onSenhaChange(novaSenha: String) {
        senha.value = novaSenha
    }

    var nome: MutableState<String> = mutableStateOf("")
        private set

    var cpf: MutableState<String> = mutableStateOf("")
        private set

    var telefone: MutableState<String> = mutableStateOf("")
        private set

    fun onNomeChange(novoNome: String) {
        nome.value = novoNome
    }

    fun onCpfChange(novoCpf: String) {
        cpf.value = novoCpf
    }

    fun onTelefoneChange(novoTelefone: String) {
        telefone.value = novoTelefone
    }

    var nomeEmpresa: MutableState<String> = mutableStateOf("")
        private set

    var cnpj: MutableState<String> = mutableStateOf("")
        private set

    var endereco: MutableState<String> = mutableStateOf("")
        private set

    fun onNomeEmpresaChange(novoNome: String) {
        nomeEmpresa.value = novoNome
    }

    fun onCnpjChange(novoCnpj: String) {
        cnpj.value = novoCnpj
    }

    fun onEnderecoChange(novoEndereco: String) {
        endereco.value = novoEndereco
    }

    private fun limparCamposCatador() {
        nome.value = ""
        cpf.value = ""
        telefone.value = ""
        email.value = ""
        senha.value = ""
    }

    private fun limparCamposEmpresa() {
        nomeEmpresa.value = ""
        cnpj.value = ""
        endereco.value = ""
        email.value = ""
        senha.value = ""
    }

    fun cadastrarCatador(onResult: (Boolean, String?) -> Unit) {
        if (
            nome.value.isBlank() ||
            cpf.value.isBlank() ||
            telefone.value.isBlank() ||
            email.value.isBlank() ||
            senha.value.isBlank()
        ) {
            onResult(false, "Preencha todos os campos")
            return
        }

        viewModelScope.launch {
            try {
                val usuarioExistente = repository.buscarPorEmail(email.value)
                if (usuarioExistente != null) {
                    onResult(false, "Este email já está cadastrado")
                    return@launch
                }

                val usuario = UserEntity(
                    nome = nome.value,
                    cpf = cpf.value,
                    telefone = telefone.value,
                    email = email.value,
                    senha = senha.value,
                    tipo = "catador"
                )

                repository.cadastrar(usuario)
                limparCamposCatador()
                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro ao cadastrar catador")
            }
        }
    }

    fun cadastrarEmpresa(onResult: (Boolean, String?) -> Unit) {
        if (
            nomeEmpresa.value.isBlank() ||
            cnpj.value.isBlank() ||
            endereco.value.isBlank() ||
            email.value.isBlank() ||
            senha.value.isBlank()
        ) {
            onResult(false, "Preencha todos os campos")
            return
        }

        viewModelScope.launch {
            try {
                val usuarioExistente = repository.buscarPorEmail(email.value)
                if (usuarioExistente != null) {
                    onResult(false, "Este email já está cadastrado")
                    return@launch
                }

                val usuario = UserEntity(
                    nomeEmpresa = nomeEmpresa.value,
                    cnpj = cnpj.value,
                    endereco = endereco.value,
                    email = email.value,
                    senha = senha.value,
                    tipo = "empresa"
                )

                repository.cadastrar(usuario)
                limparCamposEmpresa()
                onResult(true, null)
            } catch (e: Exception) {
                onResult(false, e.message ?: "Erro ao cadastrar empresa")
            }
        }
    }

    fun login(onResult: (Boolean, String?) -> Unit) {
        if (email.value.isBlank() || senha.value.isBlank()) {
            onResult(false, null)
            return
        }

        viewModelScope.launch {
            try {
                val usuario = repository.login(email.value, senha.value)
                if (usuario != null) {

                    if (usuario.tipo == "empresa") {

                        SessionManager.empresa = usuario

                    }
                    if (usuario.tipo == "catador") {

                        SessionManager.catador = usuario

                    }

                    onResult(true, usuario.tipo)

                } else {

                    onResult(false, null)

                }
            } catch (e: Exception) {
                onResult(false, null)
            }
        }
    }
}
