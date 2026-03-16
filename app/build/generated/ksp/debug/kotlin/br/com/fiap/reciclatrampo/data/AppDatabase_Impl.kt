package br.com.fiap.reciclatrampo.`data`

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import br.com.fiap.reciclatrampo.`data`.dao.ColetaDao
import br.com.fiap.reciclatrampo.`data`.dao.ColetaDao_Impl
import br.com.fiap.reciclatrampo.`data`.dao.UserDao
import br.com.fiap.reciclatrampo.`data`.dao.UserDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _userDao: Lazy<UserDao> = lazy {
    UserDao_Impl(this)
  }


  private val _coletaDao: Lazy<ColetaDao> = lazy {
    ColetaDao_Impl(this)
  }


  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "684656304b5b7edfe6de9b5d1538368a", "39fc3097cb2d93e59a9384d7a6b9345c") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `users` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `nome` TEXT NOT NULL, `email` TEXT NOT NULL, `senha` TEXT NOT NULL, `tipo` TEXT NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `coletas` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `material` TEXT NOT NULL, `pesoEstimado` REAL NOT NULL, `endereco` TEXT NOT NULL, `data` INTEGER NOT NULL, `hora` TEXT NOT NULL, `status` TEXT NOT NULL, `empresaId` INTEGER NOT NULL, `catadorId` INTEGER)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '684656304b5b7edfe6de9b5d1538368a')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `users`")
        connection.execSQL("DROP TABLE IF EXISTS `coletas`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsUsers: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsUsers.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("nome", TableInfo.Column("nome", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("email", TableInfo.Column("email", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("senha", TableInfo.Column("senha", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsUsers.put("tipo", TableInfo.Column("tipo", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysUsers: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesUsers: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoUsers: TableInfo = TableInfo("users", _columnsUsers, _foreignKeysUsers,
            _indicesUsers)
        val _existingUsers: TableInfo = read(connection, "users")
        if (!_infoUsers.equals(_existingUsers)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |users(br.com.fiap.reciclatrampo.model.User).
              | Expected:
              |""".trimMargin() + _infoUsers + """
              |
              | Found:
              |""".trimMargin() + _existingUsers)
        }
        val _columnsColetas: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsColetas.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("material", TableInfo.Column("material", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("pesoEstimado", TableInfo.Column("pesoEstimado", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("endereco", TableInfo.Column("endereco", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("data", TableInfo.Column("data", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("hora", TableInfo.Column("hora", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("empresaId", TableInfo.Column("empresaId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsColetas.put("catadorId", TableInfo.Column("catadorId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysColetas: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesColetas: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoColetas: TableInfo = TableInfo("coletas", _columnsColetas, _foreignKeysColetas,
            _indicesColetas)
        val _existingColetas: TableInfo = read(connection, "coletas")
        if (!_infoColetas.equals(_existingColetas)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |coletas(br.com.fiap.reciclatrampo.model.Coleta).
              | Expected:
              |""".trimMargin() + _infoColetas + """
              |
              | Found:
              |""".trimMargin() + _existingColetas)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "users", "coletas")
  }

  public override fun clearAllTables() {
    super.performClear(false, "users", "coletas")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(UserDao::class, UserDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ColetaDao::class, ColetaDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun userDao(): UserDao = _userDao.value

  public override fun coletaDao(): ColetaDao = _coletaDao.value
}
