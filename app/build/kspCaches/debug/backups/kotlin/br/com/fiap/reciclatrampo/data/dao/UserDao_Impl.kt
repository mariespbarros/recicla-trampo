package br.com.fiap.reciclatrampo.`data`.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import br.com.fiap.reciclatrampo.model.User
import br.com.fiap.reciclatrampo.model.UserType
import javax.`annotation`.processing.Generated
import kotlin.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class UserDao_Impl(
  __db: RoomDatabase,
) : UserDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfUser: EntityInsertAdapter<User>

  private val __deleteAdapterOfUser: EntityDeleteOrUpdateAdapter<User>

  private val __updateAdapterOfUser: EntityDeleteOrUpdateAdapter<User>
  init {
    this.__db = __db
    this.__insertAdapterOfUser = object : EntityInsertAdapter<User>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `users` (`id`,`nome`,`email`,`senha`,`tipo`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.nome)
        statement.bindText(3, entity.email)
        statement.bindText(4, entity.senha)
        statement.bindText(5, __UserType_enumToString(entity.tipo))
      }
    }
    this.__deleteAdapterOfUser = object : EntityDeleteOrUpdateAdapter<User>() {
      protected override fun createQuery(): String = "DELETE FROM `users` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id.toLong())
      }
    }
    this.__updateAdapterOfUser = object : EntityDeleteOrUpdateAdapter<User>() {
      protected override fun createQuery(): String =
          "UPDATE OR ABORT `users` SET `id` = ?,`nome` = ?,`email` = ?,`senha` = ?,`tipo` = ? WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: User) {
        statement.bindLong(1, entity.id.toLong())
        statement.bindText(2, entity.nome)
        statement.bindText(3, entity.email)
        statement.bindText(4, entity.senha)
        statement.bindText(5, __UserType_enumToString(entity.tipo))
        statement.bindLong(6, entity.id.toLong())
      }
    }
  }

  public override suspend fun insert(user: User): Unit = performSuspending(__db, false, true) {
      _connection ->
    __insertAdapterOfUser.insert(_connection, user)
  }

  public override suspend fun delete(user: User): Unit = performSuspending(__db, false, true) {
      _connection ->
    __deleteAdapterOfUser.handle(_connection, user)
  }

  public override suspend fun update(user: User): Unit = performSuspending(__db, false, true) {
      _connection ->
    __updateAdapterOfUser.handle(_connection, user)
  }

  public override suspend fun login(email: String, senha: String): User? {
    val _sql: String = "SELECT * FROM users WHERE email = ? AND senha = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, email)
        _argIndex = 2
        _stmt.bindText(_argIndex, senha)
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfNome: Int = getColumnIndexOrThrow(_stmt, "nome")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfSenha: Int = getColumnIndexOrThrow(_stmt, "senha")
        val _cursorIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpNome: String
          _tmpNome = _stmt.getText(_cursorIndexOfNome)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpSenha: String
          _tmpSenha = _stmt.getText(_cursorIndexOfSenha)
          val _tmpTipo: UserType
          _tmpTipo = __UserType_stringToEnum(_stmt.getText(_cursorIndexOfTipo))
          _result = User(_tmpId,_tmpNome,_tmpEmail,_tmpSenha,_tmpTipo)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<User> {
    val _sql: String = "SELECT * FROM users"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfNome: Int = getColumnIndexOrThrow(_stmt, "nome")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfSenha: Int = getColumnIndexOrThrow(_stmt, "senha")
        val _cursorIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _result: MutableList<User> = mutableListOf()
        while (_stmt.step()) {
          val _item: User
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpNome: String
          _tmpNome = _stmt.getText(_cursorIndexOfNome)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpSenha: String
          _tmpSenha = _stmt.getText(_cursorIndexOfSenha)
          val _tmpTipo: UserType
          _tmpTipo = __UserType_stringToEnum(_stmt.getText(_cursorIndexOfTipo))
          _item = User(_tmpId,_tmpNome,_tmpEmail,_tmpSenha,_tmpTipo)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Int): User? {
    val _sql: String = "SELECT * FROM users WHERE id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id.toLong())
        val _cursorIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _cursorIndexOfNome: Int = getColumnIndexOrThrow(_stmt, "nome")
        val _cursorIndexOfEmail: Int = getColumnIndexOrThrow(_stmt, "email")
        val _cursorIndexOfSenha: Int = getColumnIndexOrThrow(_stmt, "senha")
        val _cursorIndexOfTipo: Int = getColumnIndexOrThrow(_stmt, "tipo")
        val _result: User?
        if (_stmt.step()) {
          val _tmpId: Int
          _tmpId = _stmt.getLong(_cursorIndexOfId).toInt()
          val _tmpNome: String
          _tmpNome = _stmt.getText(_cursorIndexOfNome)
          val _tmpEmail: String
          _tmpEmail = _stmt.getText(_cursorIndexOfEmail)
          val _tmpSenha: String
          _tmpSenha = _stmt.getText(_cursorIndexOfSenha)
          val _tmpTipo: UserType
          _tmpTipo = __UserType_stringToEnum(_stmt.getText(_cursorIndexOfTipo))
          _result = User(_tmpId,_tmpNome,_tmpEmail,_tmpSenha,_tmpTipo)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  private fun __UserType_enumToString(_value: UserType): String = when (_value) {
    UserType.EMPRESA -> "EMPRESA"
    UserType.CATADOR -> "CATADOR"
  }

  private fun __UserType_stringToEnum(_value: String): UserType = when (_value) {
    "EMPRESA" -> UserType.EMPRESA
    "CATADOR" -> UserType.CATADOR
    else -> throw IllegalArgumentException("Can't convert value to enum, unknown value: " + _value)
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
