package br.com.fiap.reciclatrampo.data

import androidx.room.TypeConverter
import br.com.fiap.reciclatrampo.model.ColetaStatus
import java.time.LocalDate
import java.time.LocalTime

class Converters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate): Long {
        return date.toEpochDay()
    }

    @TypeConverter
    fun toLocalDate(value: Long): LocalDate {
        return LocalDate.ofEpochDay(value)
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime): String {
        return time.toString()
    }

    @TypeConverter
    fun toLocalTime(value: String): LocalTime {
        return LocalTime.parse(value)
    }

    @TypeConverter
    fun fromStatus(status: ColetaStatus): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(value: String): ColetaStatus {
        return ColetaStatus.valueOf(value)
    }
}