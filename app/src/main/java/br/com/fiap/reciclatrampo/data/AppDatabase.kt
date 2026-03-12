package br.com.fiap.reciclatrampo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.fiap.reciclatrampo.data.dao.ColetaDao
import br.com.fiap.reciclatrampo.data.dao.UserDao
import br.com.fiap.reciclatrampo.model.Coleta
import br.com.fiap.reciclatrampo.model.User

@Database(entities = [User::class, Coleta::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun coletaDao(): ColetaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "recicla_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }
}