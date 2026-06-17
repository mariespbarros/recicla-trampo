package br.com.fiap.reciclatrampo.data.auth.repository

import br.com.fiap.reciclatrampo.data.auth.UserDao
import br.com.fiap.reciclatrampo.data.auth.UserEntity

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

    suspend fun buscarEmpresa(id: Int): UserEntity? {
        return userDao.buscarPorId(id)
    }

}

