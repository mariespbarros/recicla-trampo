package br.com.fiap.reciclatrampo.data.repository

import br.com.fiap.reciclatrampo.data.dao.ColetaDao
import br.com.fiap.reciclatrampo.model.Coleta
import kotlinx.coroutines.flow.Flow

class ColetaRepository(private val dao: ColetaDao) {

    suspend fun solicitarColeta(coleta: Coleta) {
        dao.solicitarColeta(coleta)
    }

    suspend fun aceitarColeta(coleta: Coleta) {
        dao.aceitarColeta(coleta)
    }

    fun getByEmpresa(empresaId: Int): Flow<List<Coleta>> {
        return dao.getByEmpresa(empresaId)
    }

    fun getByCatador(catadorId: Int): Flow<List<Coleta>> {
        return dao.getByCatador(catadorId)
    }

    fun listarDisponiveis(): Flow<List<Coleta>> {
        return dao.listarDisponiveis()
    }
}