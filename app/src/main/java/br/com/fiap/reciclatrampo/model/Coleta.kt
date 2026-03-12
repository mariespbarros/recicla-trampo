package br.com.fiap.reciclatrampo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "coletas")
data class Coleta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val material: String,
    val pesoEstimado: Double,
    val endereco: String,
    val data: LocalDate,
    val hora: LocalTime,
    val status: ColetaStatus, // SOLICITADA, ACEITA, FINALIZADA

    val empresaId: Int,
    val catadorId: Int? = null // pode ser nulo no início
)