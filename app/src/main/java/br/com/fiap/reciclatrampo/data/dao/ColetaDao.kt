package br.com.fiap.reciclatrampo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.reciclatrampo.model.Coleta

@Dao
interface ColetaDao {
    @Insert
    suspend fun solicitarColeta(coleta: Coleta)

    @Update
    suspend fun aceitarColeta(coleta: Coleta)

    @Query("SELECT * FROM coletas")
    suspend fun getAll(): List<Coleta>

    @Query("SELECT * FROM coletas WHERE id = :id")
    suspend fun getById(id: Int): Coleta?

    @Delete
    suspend fun delete(coleta: Coleta)
}