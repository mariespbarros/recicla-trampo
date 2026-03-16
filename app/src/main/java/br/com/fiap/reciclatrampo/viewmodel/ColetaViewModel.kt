package br.com.fiap.reciclatrampo.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import br.com.fiap.reciclatrampo.data.repository.ColetaRepository
import br.com.fiap.reciclatrampo.model.Coleta
import br.com.fiap.reciclatrampo.model.ColetaStatus
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime

open class ColetaViewModel(private val repository: ColetaRepository) : ViewModel() {

    fun listarPorEmpresa(empresaId: Int): Flow<List<Coleta>> {
        return repository.getByEmpresa(empresaId)
    }

    fun listarDisponiveis(): Flow<List<Coleta>> {
        return repository.listarDisponiveis()
    }

    fun listarPorCatador(catadorId: Int): Flow<List<Coleta>> {
        // Como o DAO ainda não tem essa query específica por catador,
        // vamos usar o getAll e filtrar ou você pode adicionar a query no DAO futuramente.
        // Por enquanto, retornaremos todas onde o catadorId bate.
        return repository.getByCatador(catadorId)
    }

    fun aceitarColeta(coleta: Coleta, catadorId: Int) {
        viewModelScope.launch {
            val coletaAtualizada = coleta.copy(
                status = ColetaStatus.ACEITA,
                catadorId = catadorId
            )
            repository.aceitarColeta(coletaAtualizada)
            Log.d("COLETA_DEBUG", "Coleta aceita pelo catador $catadorId")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun criarColeta(
        material: String,
        endereco: String,
        empresaId: Int,
        pesoEstimado: Double,
        data: LocalDate,
        hora: LocalTime
    ) {

        viewModelScope.launch {

            repository.solicitarColeta(
                Coleta(
                    material = material,
                    endereco = endereco,
                    status = ColetaStatus.SOLICITADA,
                    empresaId = empresaId,
                    data = data,
                    hora = hora,
                    pesoEstimado = pesoEstimado
                )
            )
            Log.d("COLETA_DEBUG", "Criando coleta")
        }
    }
}