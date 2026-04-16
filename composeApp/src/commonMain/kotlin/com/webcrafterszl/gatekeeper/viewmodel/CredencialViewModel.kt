package com.webcrafterszl.gatekeeper.viewmodel

import com.webcrafterszl.gatekeeper.data.model.CredencialRFID
import com.webcrafterszl.gatekeeper.data.remote.RepositorioRemoto

class CredencialViewModel(
    repository: RepositorioRemoto = RepositorioRemoto(),
) : BaseCrudViewModel<CredencialRFID>(
    initialFormValues = mapOf(
        "id" to "",
        "codigoHexadecimal" to "",
        "nivelAcesso" to "0",
        "horaUltimaLeitura" to "",
        "bloqueada" to "false",
    ),
    toEntity = { values ->
        CredencialRFID(
            id = values["id"]?.toIntOrNull() ?: 0,
            codigoHexadecimal = values["codigoHexadecimal"].orEmpty(),
            nivelAcesso = values["nivelAcesso"]?.toIntOrNull() ?: 0,
            horaUltimaLeitura = values["horaUltimaLeitura"].orEmpty(),
            bloqueada = values["bloqueada"]?.equals("true", ignoreCase = true) == true,
        )
    },
    fromEntity = { item ->
        mapOf(
            "id" to item.id.toString(),
            "codigoHexadecimal" to item.codigoHexadecimal,
            "nivelAcesso" to item.nivelAcesso.toString(),
            "horaUltimaLeitura" to item.horaUltimaLeitura,
            "bloqueada" to item.bloqueada.toString(),
        )
    },
    carregarItens = { repository.listarCredenciais() },
    salvarItem = { item -> repository.salvarCredencial(item) },
    excluirItem = { item -> repository.excluirCredencial(item.id) },
)

