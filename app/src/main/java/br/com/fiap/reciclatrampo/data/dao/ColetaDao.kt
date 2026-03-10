package br.com.fiap.reciclatrampo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.reciclatrampo.model.Coleta
import kotlinx.coroutines.flow.Flow

@Dao
interface ColetaDao {
    @Insert
    suspend fun solicitarColeta(coleta: Coleta)

    @Update
    suspend fun aceitarColeta(coleta: Coleta)

    @Query("SELECT * FROM coletas")
    fun getAll(): Flow<List<Coleta>>

    @Query("SELECT * FROM coletas WHERE id = :id")
    suspend fun getById(id: Int): Coleta?

    @Delete
    suspend fun delete(coleta: Coleta)

    @Query("SELECT * FROM coletas WHERE empresaId = :empresaId")
    fun getByEmpresa(empresaId: Int): Flow<List<Coleta>>

    @Query("SELECT * FROM coletas WHERE status = 'SOLICITADA'")
    fun listarDisponiveis(): Flow<List<Coleta>>
}