package br.com.fiap.reciclatrampo.data

import br.com.fiap.reciclatrampo.data.auth.UserEntity

object SessionManager {
    var catador: UserEntity? = null
    var empresa: UserEntity? = null
}