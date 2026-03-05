package br.com.fiap.reciclatrampo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coletas")
data class Coleta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val material: String,
    val pesoEstimado: Double,
    val endereco: String,
    val status: String,
    val empresaId: Int,
    val catadorId: Int? = null // pode ser nulo no início
)