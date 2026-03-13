package br.com.fiap.logincadastro.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.logincadastro.data.UserEntity
import br.com.fiap.logincadastro.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: UserRepository
) : ViewModel() {

  //  Estado comum

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

  //  Cadastro catador

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

    fun cadastrarCatador(): Boolean {

        if (
            nome.value.isBlank() ||
            cpf.value.isBlank() ||
            telefone.value.isBlank() ||
            email.value.isBlank() ||
            senha.value.isBlank()
        ) {
            return false
        }

        viewModelScope.launch {

            val usuario = UserEntity(
                nome = nome.value,
                cpf = cpf.value,
                telefone = telefone.value,
                email = email.value,
                senha = senha.value,
                tipo = "catador"
            )

            repository.cadastrar(usuario)
        }

        return true

    }

   // Cadastro empresa

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

    fun cadastrarEmpresa(): Boolean {

        if (
            nomeEmpresa.value.isBlank() ||
            cnpj.value.isBlank() ||
            endereco.value.isBlank() ||
            email.value.isBlank() ||
            senha.value.isBlank()
        ) {
            return false
        }

        viewModelScope.launch {

            val usuario = UserEntity(
                nomeEmpresa = nomeEmpresa.value,
                cnpj = cnpj.value,
                endereco = endereco.value,
                email = email.value,
                senha = senha.value,
                tipo = "empresa"
            )

            repository.cadastrar(usuario)
        }

        return true

    }

    //Login

    fun login(onResult: (Boolean) -> Unit) {

        if (
            email.value.isBlank() ||
            senha.value.isBlank()
        ) {
            onResult(false)
            return
        }

        viewModelScope.launch {

            val usuario = repository.login(
                email.value,
                senha.value
            )

            onResult(usuario != null)
        }

    }

}