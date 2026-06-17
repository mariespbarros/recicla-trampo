package br.com.fiap.reciclatrampo.data.auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    suspend fun inserir(usuario: UserEntity)

    @Query("SELECT * FROM usuarios WHERE email = :email AND senha = :senha LIMIT 1")
    suspend fun login(email: String, senha: String): UserEntity?

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun buscarPorEmail(email: String): UserEntity?

    @Query("SELECT * FROM usuarios WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: Int): UserEntity?

}