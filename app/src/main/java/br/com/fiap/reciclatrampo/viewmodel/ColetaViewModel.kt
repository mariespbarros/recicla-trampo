package br.com.fiap.reciclatrampo.viewmodel

import android.os.Build
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun criarColeta(material: String, endereco: String, empresaId: Int,
                    pesoEstimado: Double, data: LocalDate,
                    hora: LocalTime) {
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
        }
    }
}