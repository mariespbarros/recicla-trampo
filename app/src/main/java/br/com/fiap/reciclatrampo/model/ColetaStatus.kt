package br.com.fiap.reciclatrampo.model

enum class ColetaStatus {

    SOLICITADA,
    ACEITA,
    FINALIZADA;

    fun descricao(): String {
        return when (this) {
            SOLICITADA -> "Aguardando coletor"
            ACEITA -> "Coletor a caminho"
            FINALIZADA -> "Coleta finalizada"
        }
    }
}