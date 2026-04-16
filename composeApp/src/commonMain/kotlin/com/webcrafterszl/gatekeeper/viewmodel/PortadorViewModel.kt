package com.webcrafterszl.gatekeeper.viewmodel

import com.webcrafterszl.gatekeeper.data.model.Portador
import com.webcrafterszl.gatekeeper.data.remote.RepositorioRemoto

class PortadorViewModel(
    repository: RepositorioRemoto = RepositorioRemoto(),
) : BaseCrudViewModel<Portador>(
    initialFormValues = mapOf(
        "id" to "",
        "nomeCompleto" to "",
        "email" to "",
        "dataContratacao" to "",
        "acessoAtivo" to "true",
    ),
    toEntity = { values ->
        Portador(
            id = values["id"]?.toIntOrNull() ?: 0,
            nomeCompleto = values["nomeCompleto"].orEmpty(),
            email = values["email"].orEmpty(),
            dataContratacao = values["dataContratacao"].orEmpty(),
            acessoAtivo = values["acessoAtivo"]?.equals("true", ignoreCase = true) == true,
        )
    },
    fromEntity = { item ->
        mapOf(
            "id" to item.id.toString(),
            "nomeCompleto" to item.nomeCompleto,
            "email" to item.email,
            "dataContratacao" to item.dataContratacao,
            "acessoAtivo" to item.acessoAtivo.toString(),
        )
    },
    carregarItens = { repository.listarPortadores() },
    salvarItem = { item -> repository.salvarPortador(item) },
    excluirItem = { item -> repository.excluirPortador(item.id) },
)

