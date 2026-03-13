package br.com.fiap.logincadastro.data.repository

import br.com.fiap.logincadastro.data.UserDao
import br.com.fiap.logincadastro.data.UserEntity

class UserRepository(private val userDao: UserDao) {


    suspend fun cadastrar(usuario: UserEntity) {
        userDao.inserir(usuario)
    }

    suspend fun login(email: String, senha: String): UserEntity? {
        return userDao.login(email, senha)
    }

    suspend fun buscarPorEmail(email: String): UserEntity? {
        return userDao.buscarPorEmail(email)
    }

}

