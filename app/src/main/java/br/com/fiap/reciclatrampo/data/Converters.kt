package br.com.fiap.reciclatrampo.data

import androidx.room.TypeConverter
import br.com.fiap.reciclatrampo.model.ColetaStatus
import java.time.LocalDate
import java.time.LocalTime

class Converters {

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(it) }
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? {
        return time?.toString()
    }

    @TypeConverter
    fun toLocalTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(it) }
    }

    @TypeConverter
    fun fromStatus(status: ColetaStatus): String {
        return status.name
    }

    @TypeConverter
    fun toStatus(status: String): ColetaStatus {
        return ColetaStatus.valueOf(status)
    }
}