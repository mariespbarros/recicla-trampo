package br.com.fiap.logincadastro.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String? = null,
    val cpf: String? = null,
    val telefone: String? = null,

    val nomeEmpresa: String? = null,
    val cnpj: String? = null,
    val endereco: String? = null,

    val email: String,
    val senha: String,
    val tipo: String

)