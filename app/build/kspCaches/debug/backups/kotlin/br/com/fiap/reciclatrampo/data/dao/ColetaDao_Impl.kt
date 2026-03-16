package br.com.fiap.reciclatrampo.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import br.com.fiap.reciclatrampo.`data`.Converters
import br.com.fiap.reciclatrampo.model.Coleta
import br.com.fiap.reciclatrampo.model.ColetaStatus
import java.time.LocalDate
import java.time.LocalTime
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ColetaDao_Impl(
  __db: RoomDatabase,
) : ColetaDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfColeta: EntityInsertAdapter<Coleta>

  private val __converters: Converters = Converters()

  private val __deleteAdapterOfColeta: EntityDeleteOrUpdateAdapter<Coleta>

  private val __updateAdapterOfColeta: EntityDeleteOrUpdateAdapter<Coleta>
  init {
    this.__db = __db
    this.__insertAdapterOfColeta = object : EntityInsertAdapter<Coleta>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `coletas` (`id`,`material`,`pesoEstimado`,`endereco`,`data`,`hora`,`status`,`empresaId`,`catadorId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Coleta) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.material)
        statement.bindDouble(3, entity.pesoEstimado)
        statement.bindText(4, entity.endereco)
        val _tmp: Long = __converters.fromLocalDate(entity.data)
        statement.bindLong(5, _tmp)
        val _tmp_1: String = __converters.fromLocalTime(entity.hora)
        statement.bindText(6, _tmp_1)
        val _tmp_2: String = __converters.fromStatus(entity.status)
        statement.bindText(7, _tmp_2)
        statement.bindLong(8, entity.empresaId.toLong())
        val _tmpCatadorId: Int? = entity.catadorId
        if (_tmpCatadorId == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpCatadorId.toLong())
        }
      }
    }
    this.__deleteAdapterOfColeta = object : EntityDeleteOrUpdateAdapter<Coleta>() {
      protected override fun createQuery(): String = "DELETE FROM `coletas` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Coleta) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfColeta = object : EntityDeleteOrUpdateAdapter<Coleta>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `coletas` SET `id` = ?,`material` = ?,`pesoEstimado` = ?,`endereco` = ?,`data` = ?,`hora` = ?,`status` = ?,`empresaId` = ?,`catadorId` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: Coleta) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.material)
        statement.bindDouble(3, entity.pesoEstimado)
        statement.bindText(4, entity.endereco)
        val _tmp: Long = __converters.fromLocalDate(entity.data)
        statement.bindLong(5, _tmp)
        val _tmp_1: String = __converters.fromLocalTime(entity.hora)
        statement.bindText(6, _tmp_1)
        val _tmp_2: String = __converters.fromStatus(entity.status)
        statement.bindText(7, _tmp_2)
        statement.bindLong(8, entity.empresaId.toLong())
        val _tmpCatadorId: Int? = entity.catadorId
        if (_tmpCatadorId == null) {
          statement.bindNull(9)
        } else {
          statement.bindLong(9, _tmpCatadorId.toLong())
        }
        statement.bindLong(10, entity.id.toLong())
      }
    }
  }

  public override suspend fun solicitarColeta(coleta: Coleta): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfColeta.insert(_connection, coleta)
  }

  public override suspend fun delete(coleta: Coleta): Unit = performSuspending(__db, false, true) {
      _connection ->
    __deleteAdapterOfColeta.handle(_connection, coleta)
  }

  public override suspend fun aceitarColeta(coleta: Coleta): Unit = performSuspending(__db, false,
      true) { _connection ->
    __updateAdapterOfColeta.handle(_connection, coleta)
  }

  public override fun getAll(): Flow<List<Coleta>> {
    val _sql: String = "SELECT * FROM coletas"
    return createFlow(__db, false, arrayOf("coletas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfMaterial: Int = getColumnIndexOrThrow(_stmt, "material")
        val _cursorIndexOfPesoEstimado: Int = getColumnIndexOrThrow(_stmt, "pesoEstimado")
        val _cursorIndexOfEndereco: Int = getColumnIndexOrThrow(_stmt, "endereco")
        val _cursorIndexOfData: Int = getColumnIndexOrThrow(_stmt, "data")
        val _cursorIndexOfHora: Int = getColumnIndexOrThrow(_stmt, "hora")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfEmpresaId: Int = getColumnIndexOrThrow(_stmt, "empresaId")
        val _cursorIndexOfCatadorId: Int = getColumnIndexOrThrow(_stmt, "catadorId")
        val _result: MutableList<Coleta> = mutableListOf()
        while (_stmt.step()) {
          val _item: Coleta
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpMaterial: String
          _tmpMaterial = _stmt.getText(_cursorIndexOfMaterial)
          val _tmpPesoEstimado: Double
          _tmpPesoEstimado = _stmt.getDouble(_cursorIndexOfPesoEstimado)
          val _tmpEndereco: String
          _tmpEndereco = _stmt.getText(_cursorIndexOfEndereco)
          val _tmpData: LocalDate
          val _tmp: Long
          _tmp = _stmt.getLong(_cursorIndexOfData)
          _tmpData = __converters.toLocalDate(_tmp)
          val _tmpHora: LocalTime
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfHora)
          _tmpHora = __converters.toLocalTime(_tmp_1)
          val _tmpStatus: ColetaStatus
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __converters.toStatus(_tmp_2)
          val _tmpEmpresaId: Int
          _tmpEmpresaId = _stmt.getLong(_cursorIndexOfEmpresaId).toInt()
          val _tmpCatadorId: Int?
          if (_stmt.isNull(_cursorIndexOfCatadorId)) {
            _tmpCatadorId = null
          } else {
            _tmpCatadorId = _stmt.getLong(_cursorIndexOfCatadorId).toInt()
          }
          _item =
              Coleta(_tmpId,_tmpMaterial,_tmpPesoEstimado,_tmpEndereco,_tmpData,_tmpHora,_tmpStatus,_tmpEmpresaId,_tmpCatadorId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Int): Coleta? {
    val _sql: String = "SELECT * FROM coletas WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfMaterial: Int = getColumnIndexOrThrow(_stmt, "material")
        val _cursorIndexOfPesoEstimado: Int = getColumnIndexOrThrow(_stmt, "pesoEstimado")
        val _cursorIndexOfEndereco: Int = getColumnIndexOrThrow(_stmt, "endereco")
        val _cursorIndexOfData: Int = getColumnIndexOrThrow(_stmt, "data")
        val _cursorIndexOfHora: Int = getColumnIndexOrThrow(_stmt, "hora")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfEmpresaId: Int = getColumnIndexOrThrow(_stmt, "empresaId")
        val _cursorIndexOfCatadorId: Int = getColumnIndexOrThrow(_stmt, "catadorId")
        val _result: Coleta?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpMaterial: String
          _tmpMaterial = _stmt.getText(_cursorIndexOfMaterial)
          val _tmpPesoEstimado: Double
          _tmpPesoEstimado = _stmt.getDouble(_cursorIndexOfPesoEstimado)
          val _tmpEndereco: String
          _tmpEndereco = _stmt.getText(_cursorIndexOfEndereco)
          val _tmpData: LocalDate
          val _tmp: Long
          _tmp = _stmt.getLong(_cursorIndexOfData)
          _tmpData = __converters.toLocalDate(_tmp)
          val _tmpHora: LocalTime
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfHora)
          _tmpHora = __converters.toLocalTime(_tmp_1)
          val _tmpStatus: ColetaStatus
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __converters.toStatus(_tmp_2)
          val _tmpEmpresaId: Int
          _tmpEmpresaId = _stmt.getLong(_cursorIndexOfEmpresaId).toInt()
          val _tmpCatadorId: Int?
          if (_stmt.isNull(_cursorIndexOfCatadorId)) {
            _tmpCatadorId = null
          } else {
            _tmpCatadorId = _stmt.getLong(_cursorIndexOfCatadorId).toInt()
          }
          _result =
              Coleta(_tmpId,_tmpMaterial,_tmpPesoEstimado,_tmpEndereco,_tmpData,_tmpHora,_tmpStatus,_tmpEmpresaId,_tmpCatadorId)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun getByEmpresa(empresaId: Int): Flow<List<Coleta>> {
    val _sql: String = "SELECT * FROM coletas WHERE empresaId = ?"
    return createFlow(__db, false, arrayOf("coletas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, empresaId.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfMaterial: Int = getColumnIndexOrThrow(_stmt, "material")
        val _cursorIndexOfPesoEstimado: Int = getColumnIndexOrThrow(_stmt, "pesoEstimado")
        val _cursorIndexOfEndereco: Int = getColumnIndexOrThrow(_stmt, "endereco")
        val _cursorIndexOfData: Int = getColumnIndexOrThrow(_stmt, "data")
        val _cursorIndexOfHora: Int = getColumnIndexOrThrow(_stmt, "hora")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfEmpresaId: Int = getColumnIndexOrThrow(_stmt, "empresaId")
        val _cursorIndexOfCatadorId: Int = getColumnIndexOrThrow(_stmt, "catadorId")
        val _result: MutableList<Coleta> = mutableListOf()
        while (_stmt.step()) {
          val _item: Coleta
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpMaterial: String
          _tmpMaterial = _stmt.getText(_cursorIndexOfMaterial)
          val _tmpPesoEstimado: Double
          _tmpPesoEstimado = _stmt.getDouble(_cursorIndexOfPesoEstimado)
          val _tmpEndereco: String
          _tmpEndereco = _stmt.getText(_cursorIndexOfEndereco)
          val _tmpData: LocalDate
          val _tmp: Long
          _tmp = _stmt.getLong(_cursorIndexOfData)
          _tmpData = __converters.toLocalDate(_tmp)
          val _tmpHora: LocalTime
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfHora)
          _tmpHora = __converters.toLocalTime(_tmp_1)
          val _tmpStatus: ColetaStatus
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __converters.toStatus(_tmp_2)
          val _tmpEmpresaId: Int
          _tmpEmpresaId = _stmt.getLong(_cursorIndexOfEmpresaId).toInt()
          val _tmpCatadorId: Int?
          if (_stmt.isNull(_cursorIndexOfCatadorId)) {
            _tmpCatadorId = null
          } else {
            _tmpCatadorId = _stmt.getLong(_cursorIndexOfCatadorId).toInt()
          }
          _item =
              Coleta(_tmpId,_tmpMaterial,_tmpPesoEstimado,_tmpEndereco,_tmpData,_tmpHora,_tmpStatus,_tmpEmpresaId,_tmpCatadorId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun listarDisponiveis(): Flow<List<Coleta>> {
    val _sql: String = "SELECT * FROM coletas WHERE status = 'SOLICITADA'"
    return createFlow(__db, false, arrayOf("coletas")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfMaterial: Int = getColumnIndexOrThrow(_stmt, "material")
        val _cursorIndexOfPesoEstimado: Int = getColumnIndexOrThrow(_stmt, "pesoEstimado")
        val _cursorIndexOfEndereco: Int = getColumnIndexOrThrow(_stmt, "endereco")
        val _cursorIndexOfData: Int = getColumnIndexOrThrow(_stmt, "data")
        val _cursorIndexOfHora: Int = getColumnIndexOrThrow(_stmt, "hora")
        val _cursorIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _cursorIndexOfEmpresaId: Int = getColumnIndexOrThrow(_stmt, "empresaId")
        val _cursorIndexOfCatadorId: Int = getColumnIndexOrThrow(_stmt, "catadorId")
        val _result: MutableList<Coleta> = mutableListOf()
        while (_stmt.step()) {
          val _item: Coleta
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpMaterial: String
          _tmpMaterial = _stmt.getText(_cursorIndexOfMaterial)
          val _tmpPesoEstimado: Double
          _tmpPesoEstimado = _stmt.getDouble(_cursorIndexOfPesoEstimado)
          val _tmpEndereco: String
          _tmpEndereco = _stmt.getText(_cursorIndexOfEndereco)
          val _tmpData: LocalDate
          val _tmp: Long
          _tmp = _stmt.getLong(_cursorIndexOfData)
          _tmpData = __converters.toLocalDate(_tmp)
          val _tmpHora: LocalTime
          val _tmp_1: String
          _tmp_1 = _stmt.getText(_cursorIndexOfHora)
          _tmpHora = __converters.toLocalTime(_tmp_1)
          val _tmpStatus: ColetaStatus
          val _tmp_2: String
          _tmp_2 = _stmt.getText(_cursorIndexOfStatus)
          _tmpStatus = __converters.toStatus(_tmp_2)
          val _tmpEmpresaId: Int
          _tmpEmpresaId = _stmt.getLong(_cursorIndexOfEmpresaId).toInt()
          val _tmpCatadorId: Int?
          if (_stmt.isNull(_cursorIndexOfCatadorId)) {
            _tmpCatadorId = null
          } else {
            _tmpCatadorId = _stmt.getLong(_cursorIndexOfCatadorId).toInt()
          }
          _item =
              Coleta(_tmpId,_tmpMaterial,_tmpPesoEstimado,_tmpEndereco,_tmpData,_tmpHora,_tmpStatus,_tmpEmpresaId,_tmpCatadorId)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
