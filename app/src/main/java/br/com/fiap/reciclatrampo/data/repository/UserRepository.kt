package br.com.fiap.reciclatrampo.data.repository

import br.com.fiap.reciclatrampo.data.dao.UserDao
import br.com.fiap.reciclatrampo.model.User

class UserRepository(private val dao: UserDao) {

    suspend fun inserir(user: User) {
        dao.insert(user)
    }

    suspend fun login(email: String, senha: String): User? {
        return dao.login(email, senha)
    }
}