package br.com.fiap.reciclatrampo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.fiap.reciclatrampo.data.repository.ColetaRepository

class ColetaViewModelFactory(
    private val repository: ColetaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ColetaViewModel(repository) as T
    }
}